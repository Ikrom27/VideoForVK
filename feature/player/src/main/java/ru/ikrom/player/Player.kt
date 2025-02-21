package ru.ikrom.player

import android.app.Activity
import android.content.res.Configuration
import android.os.Build
import android.view.View
import android.view.WindowInsets
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.compose.material3.Text

@Composable
fun PlayerScreen(
    videoUrl: String,
    viewModel: PlayerViewModel = hiltViewModel()
){
    viewModel.updateVideo(videoUrl)
    val videoInfo by viewModel.content.collectAsState()

    videoInfo?.let { Content(it) }
}

@Composable
fun Content(info: VideoInfo){
    val context = LocalContext.current
    val exoPlayer = remember { ExoPlayer.Builder(context).build() }
    val mediaSource = remember(info.url) {
        MediaItem.fromUri(info.url)
    }
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
    }
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }
    SetFullscreenMode(isLandscape)
    if(isLandscape){
        HorizontalPlayer(exoPlayer)
    } else {
        VerticalPlayer(exoPlayer, info.title)
    }
}

@Composable
fun HorizontalPlayer(
    exoPlayer: ExoPlayer
) {
    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                }
            },
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
        )
    }
}


@Composable
fun VerticalPlayer(
    exoPlayer: ExoPlayer,
    title: String
) {
    Column {
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun SetFullscreenMode(enabled: Boolean) {
    val context = LocalContext.current
    val activity = context as Activity
    LaunchedEffect(enabled) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            enableFullScreenApi30(enabled, activity)
        } else {
            enableFullScreenApi26(enabled, activity)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.R)
fun enableFullScreenApi30(
    enabled: Boolean,
    activity: Activity){
    activity.window?.let { window ->
        val insetsController = window.insetsController
        if (enabled) {
            insetsController?.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
        } else {
            insetsController?.show(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
        }
    }
}

fun enableFullScreenApi26(
    enabled: Boolean,
    activity: Activity
){
    val decorView = activity.window?.decorView
    decorView?.let { view ->
        if (enabled) {
            view.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )
        } else {
            view.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }
    }
}
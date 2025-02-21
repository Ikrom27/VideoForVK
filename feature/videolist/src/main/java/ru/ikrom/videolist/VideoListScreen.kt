package ru.ikrom.videolist

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.ikrom.resources.DescriptionsID
import ru.ikrom.video_usecase.models.Duration


@Composable
fun VideoListScreen(
    onVideoClick: (String) -> Unit,
    viewModel: VideoListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val refreshState by viewModel.refreshState.collectAsState()

    when(state){
        UiState.Error -> {}
        is UiState.Success -> Content(
            items = (state as UiState.Success).item,
            refreshState = refreshState,
            onRefresh = viewModel::refresh,
            onItemClick = { onVideoClick(it) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(
    items: List<VideoItem>,
    refreshState: Boolean,
    onRefresh: () -> Unit,
    onItemClick: (String) -> Unit,
){

    PullToRefreshBox(
        isRefreshing = refreshState,
        onRefresh = onRefresh,
    ) {
        LazyColumn {
            items(items) {
                ThumbnailBigItem(
                    title = it.title,
                    thumbnail = it.thumbnail,
                    duration = it.duration,
                    modifier = Modifier.clickable { onItemClick(it.id)  }
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ThumbnailBigItem(
    title: String,
    thumbnail: String,
    duration: String,
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .border(1.dp, Color.Gray.copy(alpha = 0.2f), RoundedCornerShape(24.dp)),
        ) {
            GlideImage(
                model = thumbnail,
                contentDescription = LocalContext.current.getString(DescriptionsID.VIDEO_THUMBNAIL),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = duration,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.DarkGray,
            maxLines = 1,
        )
    }
}


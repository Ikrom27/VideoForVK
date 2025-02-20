package ru.ikrom.videolist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.ikrom.resources.DescriptionsID


@Composable
fun VideoList(
    onVideoClick: (Int) -> Unit,
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
    onItemClick: (Int) -> Unit,
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
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        GlideImage(
            model = thumbnail,
            contentDescription = LocalContext.current.getString(DescriptionsID.VIDEO_THUMBNAIL),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
        )

        Text(
            text = title,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

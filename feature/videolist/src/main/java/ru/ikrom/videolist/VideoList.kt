package ru.ikrom.videolist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import ru.ikrom.resources.DescriptionsID


@Composable
fun VideoList(
    viewModel: VideoListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    when(state){
        VideoListState.Error -> {}
        is VideoListState.Success -> Content((state as VideoListState.Success).item, viewModel::refresh)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(
    items: List<VideoItem>,
    onRefresh: () -> Unit
){
    val refreshState by remember { mutableStateOf(false) }
    PullToRefreshBox(
        isRefreshing = refreshState,
        onRefresh = onRefresh,
    ) {
        LazyColumn {
            items(items) {
                ThumbnailBigItem(
                    it.title,
                    it.thumbnail,
                )
            }
        }
    }
}

@Composable
fun ThumbnailBigItem(
    title: String,
    thumbnail: String,
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = thumbnail,
            contentDescription = LocalContext.current.getString(DescriptionsID.VIDEO_THUMBNAIL),
            modifier = Modifier.fillMaxWidth().height(100.dp)
        )
        Text(title)
    }
}
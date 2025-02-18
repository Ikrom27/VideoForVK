package ru.ikrom.videolist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel


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

        }
    }
}
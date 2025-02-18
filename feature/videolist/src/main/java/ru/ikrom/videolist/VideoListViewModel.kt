package ru.ikrom.videolist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class VideoListViewModel: ViewModel() {
    fun refresh() {

    }

    private val _state = MutableStateFlow(VideoListState.Success(emptyList()))
    val state: StateFlow<VideoListState> = _state

    init {
        _state.value = VideoListState.Success(
            testList
        )
    }
}


val image = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fvisme.co%2Fblog%2Fhow-to-make-a-video%2F&psig=AOvVaw0sPTvP9MbVSJ66i95rwJCz&ust=1739971002914000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCPj535-nzYsDFQAAAAAdAAAAABAE"
val testList = listOf(
    VideoItem(
        title = "videoTitle",
        thumbnail = image,
        duration = 100000L
    ),
    VideoItem(
        title = "videoTitle",
        thumbnail = image,
        duration = 100000L
    ),
    VideoItem(
        title = "videoTitle",
        thumbnail = image,
        duration = 100000L
    ),
    VideoItem(
        title = "videoTitle",
        thumbnail = image,
        duration = 100000L
    ),
    VideoItem(
        title = "videoTitle",
        thumbnail = image,
        duration = 100000L
    )
)
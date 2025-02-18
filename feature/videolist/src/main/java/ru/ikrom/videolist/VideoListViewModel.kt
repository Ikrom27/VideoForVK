package ru.ikrom.videolist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class VideoListViewModel @Inject constructor(): ViewModel() {
    fun refresh() {

    }

    private val _state = MutableStateFlow(UiState.Success(emptyList()))
    val state: StateFlow<UiState> = _state

    init {
        _state.value = UiState.Success(
            testList
        )
    }
}


val image = "https://babich.biz/content/images/2017/01/schools-promotional-videos.jpg"
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
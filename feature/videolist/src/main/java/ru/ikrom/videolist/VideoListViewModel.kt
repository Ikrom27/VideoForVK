package ru.ikrom.videolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.ikrom.repository.IRepository
import javax.inject.Inject

@HiltViewModel
class VideoListViewModel @Inject constructor(
    private val repository: IRepository
): ViewModel() {
    fun refresh() {

    }

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Success(emptyList()))
    val state: StateFlow<UiState> = _state

    init {
        viewModelScope.launch {
            _state.value = runCatching {
                UiState.Success(repository.getPopularVideo("nature").map { VideoItem(
                    title = it.title,
                    thumbnail = it.thumbnailUrl,
                    duration = it.duration.toLong(),
                ) })
            }.getOrNull() ?:  UiState.Error
        }
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
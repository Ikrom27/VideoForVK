package ru.ikrom.videolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.ikrom.video_usecase.VideoUseCase
import javax.inject.Inject

@HiltViewModel
class VideoListViewModel @Inject constructor(
    private val useCase: VideoUseCase
): ViewModel() {
    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Success(emptyList()))
    val state: StateFlow<UiState> = _state

    private val _refreshState = MutableStateFlow(false)
    val refreshState: StateFlow<Boolean> = _refreshState

    init {
        viewModelScope.launch {
            update()
        }
    }

    private suspend fun update(){
        _state.value = runCatching {
            UiState.Success(useCase.getPopularVideo("nature").map { VideoItem(
                id = it.id.value,
                title = it.title,
                thumbnail = it.thumbnailUrl,
                duration = it.toString(),
            ) })
        }.getOrNull() ?:  UiState.Error
    }

    fun refresh() {
        _refreshState.value = true
        viewModelScope.launch {
            update()
            _refreshState.value = false
        }
    }
}
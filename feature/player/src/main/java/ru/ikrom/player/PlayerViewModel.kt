package ru.ikrom.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.ikrom.video_usecase.VideoUseCase
import ru.ikrom.video_usecase.models.ID
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val useCase: VideoUseCase
): ViewModel() {
    private var _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val state: StateFlow<UiState> = _state

    fun updateVideo(id: String) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            runCatching {
                useCase.getVideo(ID(id))
            }.onSuccess {
                _state.value = UiState.Success(
                    VideoInfo(
                        title = it.title,
                        url = it.link
                    )
                )
            }
        }
    }
}
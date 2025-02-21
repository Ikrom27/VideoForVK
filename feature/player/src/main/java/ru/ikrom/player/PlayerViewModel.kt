package ru.ikrom.player

import android.util.Log
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
    private val _content: MutableStateFlow<VideoInfo?> = MutableStateFlow(null)
    val content: StateFlow<VideoInfo?> = _content

    private val _errorState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val errorState: StateFlow<Boolean> = _errorState

    fun updateVideo(id: String) {
        viewModelScope.launch {
            runCatching {
                useCase.getVideo(ID(id))
            }.onSuccess { result ->
                _content.value = VideoInfo(
                    title = result.title,
                    url = result.link
                )
            }.onFailure { e ->
                _errorState.value = true
                Log.e(TAG, e.message.toString())
            }
        }
    }

    companion object {
        val TAG = PlayerViewModel::class.simpleName
    }
}
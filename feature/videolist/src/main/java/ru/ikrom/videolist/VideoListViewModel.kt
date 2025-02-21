package ru.ikrom.videolist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
        viewModelScope.launch(Dispatchers.IO) {
            loadSavedData()
            update()
        }
    }

    private suspend fun loadSavedData(){
        runCatching {
            useCase.getSavedVideo()
        }.onSuccess { result ->
            _state.value = UiState.Success(
                result.map { it.toVideoItem() }
            )
        }.onFailure { e ->
            Log.e(TAG, e.message.toString())
        }
    }

    private suspend fun update(){
        runCatching {
            useCase.getPopularVideo()
        }.onSuccess { result ->
            _state.value = UiState.Success(result.map { it.toVideoItem() })
        }.onFailure { e ->
            Log.e(TAG, e.message.toString())
            _state.value = UiState.Error
        }
    }

    fun refresh() {
        _refreshState.value = true
        viewModelScope.launch(Dispatchers.IO) {
            update()
            _refreshState.value = false
        }
    }

    companion object {
        val TAG = VideoListViewModel::class.simpleName
    }
}
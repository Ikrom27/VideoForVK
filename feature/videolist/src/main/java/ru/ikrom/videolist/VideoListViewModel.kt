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
    private val _content: MutableStateFlow<List<VideoItem>> = MutableStateFlow(emptyList())
    val content: StateFlow<List<VideoItem>> = _content

    private val _refreshState = MutableStateFlow(false)
    val refreshState: StateFlow<Boolean> = _refreshState

    private val _errorState = MutableStateFlow(false)
    val errorState = _errorState

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
            _content.value = result.map { it.toVideoItem() }
        }.onFailure { e ->
            Log.e(TAG, e.message.toString())
        }
    }

    private suspend fun update(){
        runCatching {
            useCase.getPopularVideo()
        }.onSuccess { result ->
            _content.value = result.map { it.toVideoItem() }
        }.onFailure { e ->
            errorState.value = true
        }
    }

    fun refresh() {
        _refreshState.value = true
        viewModelScope.launch(Dispatchers.IO) {
            update()
            _refreshState.value = false
        }
    }

    fun resetErrorState() {
        _errorState.value = false
    }

    companion object {
        val TAG = VideoListViewModel::class.simpleName
    }
}
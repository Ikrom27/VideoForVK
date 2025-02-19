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
            UiState.Success(repository.getPopularVideo("nature").map { VideoItem(
                title = it.title,
                thumbnail = it.thumbnailUrl,
                duration = it.duration.toLong(),
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
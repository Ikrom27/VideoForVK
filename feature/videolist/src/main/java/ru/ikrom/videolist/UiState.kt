package ru.ikrom.videolist

sealed class UiState {
    data object Error: UiState()
    data class Success(val item: List<VideoItem>): UiState()
}
package ru.ikrom.player

sealed class UiState {
    data object Loading: UiState()
    data class Success(val videoInfo: VideoInfo): UiState()
}
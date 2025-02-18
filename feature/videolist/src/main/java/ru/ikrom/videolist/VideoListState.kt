package ru.ikrom.videolist

sealed class VideoListState {
    data object Error: VideoListState()
    data class Success(val item: List<VideoItem>): VideoListState()
}
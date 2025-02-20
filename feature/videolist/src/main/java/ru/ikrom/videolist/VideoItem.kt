package ru.ikrom.videolist

data class VideoItem(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val duration: Long,
)
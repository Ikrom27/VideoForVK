package ru.ikrom.video_usecase.models

data class VideoModel(
    val id: ID,
    val title: String,
    val thumbnailUrl: String,
    val duration: Duration,
    val link: String
)
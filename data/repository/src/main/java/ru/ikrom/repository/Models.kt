package ru.ikrom.repository

data class VideoModel(
    val id: Int,
    val title: String,
    val thumbnailUrl: String,
    val duration: Int,
    val link: String
)
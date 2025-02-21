package ru.ikrom.videolist

import ru.ikrom.video_usecase.models.VideoModel

data class VideoItem(
    val id: String,
    val title: String,
    val thumbnail: String,
    val duration: String,
)

internal fun VideoModel.toVideoItem() = VideoItem(
    id = id.value,
    title = title,
    thumbnail = thumbnailUrl,
    duration = duration.toString()
)
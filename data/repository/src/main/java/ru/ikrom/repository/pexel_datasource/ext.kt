package ru.ikrom.repository.pexel_datasource

import ru.ikrom.video_usecase.models.Duration
import ru.ikrom.video_usecase.models.ID
import ru.ikrom.video_usecase.models.VideoModel

fun PexelsVideo.toModel(): VideoModel {
    return VideoModel(
        id = ID(id),
        title = "my video",
        thumbnailUrl = image,
        duration = Duration(duration),
        link = videoFiles.first().link
    )
}
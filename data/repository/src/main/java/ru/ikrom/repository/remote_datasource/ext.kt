package ru.ikrom.repository.remote_datasource

import ru.ikrom.video_usecase.models.Duration
import ru.ikrom.video_usecase.models.ID
import ru.ikrom.video_usecase.models.VideoModel

internal fun PexelsVideo.toModel(): VideoModel {
    return VideoModel(
        id = ID(id),
        title = user.name,
        thumbnailUrl = image,
        duration = Duration(duration),
        link = videoFiles.first().link
    )
}
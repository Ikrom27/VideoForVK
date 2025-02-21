package ru.ikrom.repository.local_datasource

import ru.ikrom.video_usecase.models.Duration
import ru.ikrom.video_usecase.models.ID
import ru.ikrom.video_usecase.models.VideoModel

internal fun VideoEntity.toModel(): VideoModel {
    return VideoModel(
        id = ID(id),
        title = "my video",
        thumbnailUrl = thumbnailUrl,
        duration = Duration(duration),
        link = link
    )
}
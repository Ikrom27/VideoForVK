package ru.ikrom.repository.pexel_datasource

import android.net.Uri
import ru.ikrom.repository.Duration
import ru.ikrom.repository.ID
import ru.ikrom.repository.VideoModel

fun PexelsVideo.toModel(): VideoModel {
    return VideoModel(
        id = ID(id),
        title = "my video",
        thumbnailUrl = Uri.parse(image),
        duration = Duration(duration),
        link = Uri.parse(videoFiles.first().link)
    )
}
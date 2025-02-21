package ru.ikrom.video_usecase

import ru.ikrom.video_usecase.models.ID
import ru.ikrom.video_usecase.models.VideoModel

interface IRepository {
    suspend fun getPopularVideo(query: String): List<VideoModel>
    suspend fun getVideo(id: ID): VideoModel
}
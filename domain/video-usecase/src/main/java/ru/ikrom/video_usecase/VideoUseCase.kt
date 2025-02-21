package ru.ikrom.video_usecase

import ru.ikrom.video_usecase.models.ID
import ru.ikrom.video_usecase.models.VideoModel

class VideoUseCase(
    private val repository: IRepository
) {
    suspend fun getPopularVideo(query: String = EXAMPLE_QUERY): List<VideoModel> {
        return repository.getPopularVideo(query)
    }

    suspend fun getSavedVideo(): List<VideoModel> {
        return repository.getSavedVideo()
    }

    suspend fun getVideo(id: ID): VideoModel {
        return repository.getVideo(id)
    }

    companion object {
        const val EXAMPLE_QUERY = "Cyberpunk"
    }
}
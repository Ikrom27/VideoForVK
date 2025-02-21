package ru.ikrom.repository.remote_datasource

import javax.inject.Inject

internal class PexelDataSource @Inject constructor(
    private val pexelService: PexelApi
) {

    suspend fun fetchPopularVideo(query: String): List<PexelsVideo>{
        return pexelService.getPopularVideos(query).videos
    }

    suspend fun getVideo(id: Int): PexelsVideo {
        return pexelService.getVideo(id)
    }
}
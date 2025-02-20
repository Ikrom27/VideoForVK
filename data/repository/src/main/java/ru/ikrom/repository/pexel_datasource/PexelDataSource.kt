package ru.ikrom.repository.pexel_datasource

import retrofit2.Retrofit
import ru.ikrom.repository.BuildConfig
import javax.inject.Inject

class PexelDataSource @Inject constructor(
    private val pexelService: PexelApi
) {

    suspend fun fetchPopularVideo(query: String): List<PexelsVideo>{
        return pexelService.getPopularVideos(query).videos
    }

    suspend fun getVideo(id: Int): PexelsVideo {
        return pexelService.getVideo(id)
    }
}
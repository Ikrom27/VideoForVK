package ru.ikrom.repository.pexel_datasource

import retrofit2.Retrofit
import ru.ikrom.repository.BuildConfig
import javax.inject.Inject

class PexelDataSource @Inject constructor(
    private val retrofit: Retrofit
) {

    suspend fun fetchPopularVideo(): List<PexelsVideo>{
        val api = retrofit.create(PexelApi::class.java)
        return api.getPopularVideos(API_KEY).videos
    }

    companion object {
        const val API_KEY = BuildConfig.PEXEL_API_KEY
    }
}
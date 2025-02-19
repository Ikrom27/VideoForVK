package ru.ikrom.repository.pexel_datasource

import retrofit2.http.GET
import retrofit2.http.Header


interface PexelApi {
    @GET("videos/popular")
    suspend fun getPopularVideos(
        @Header("Authorization") apiKey: String
    ): PexelsVideoResponse
}

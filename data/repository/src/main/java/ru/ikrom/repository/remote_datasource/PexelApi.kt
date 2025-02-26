package ru.ikrom.repository.remote_datasource

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


internal interface PexelApi {
    @GET("videos/search")
    suspend fun getPopularVideos(
        @Query("query") query: String,
        @Query("orientation") orientation: String = PexelOrientation.LANDSCAPE,
        @Query("per_page") perPage: Int = 10
    ): PexelsVideoResponse

    @GET("videos/videos/{id}")
    suspend fun getVideo(@Path("id") id: Int,): PexelsVideo
}

object PexelOrientation {
    const val LANDSCAPE = "landscape"
}

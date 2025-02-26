package ru.ikrom.repository.remote_datasource

import com.google.gson.annotations.SerializedName

internal data class PexelsVideoResponse(
    @SerializedName("videos") val videos: List<PexelsVideo>
)

internal data class PexelsVideo(
    @SerializedName("id") val id: Int,
    @SerializedName("url") val url: String,
    @SerializedName("image") val image: String,
    @SerializedName("duration") val duration: Int,
    @SerializedName("user") val user: User,
    @SerializedName("video_files") val videoFiles: List<PexelsVideoFile>
)

internal data class PexelsVideoFile(
    @SerializedName("id") val id: Int,
    @SerializedName("quality") val quality: String,
    @SerializedName("file_type") val fileType: String,
    @SerializedName("link") val link: String
)

internal data class User(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

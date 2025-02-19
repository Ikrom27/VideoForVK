package ru.ikrom.repository.pexel_datasource

import com.google.gson.annotations.SerializedName

data class PexelsVideoResponse(
    @SerializedName("videos") val videos: List<PexelsVideo>
)

data class PexelsVideo(
    @SerializedName("id") val id: Int,
    @SerializedName("url") val url: String,
    @SerializedName("image") val image: String,
    @SerializedName("duration") val duration: Int,
    @SerializedName("video_files") val videoFiles: List<PexelsVideoFile>
)

data class PexelsVideoFile(
    @SerializedName("id") val id: Int,
    @SerializedName("quality") val quality: String,
    @SerializedName("file_type") val fileType: String,
    @SerializedName("link") val link: String
)

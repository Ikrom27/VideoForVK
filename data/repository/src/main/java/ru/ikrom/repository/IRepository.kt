package ru.ikrom.repository

interface IRepository {
    suspend fun getPopularVideo(query: String): List<VideoModel>
}
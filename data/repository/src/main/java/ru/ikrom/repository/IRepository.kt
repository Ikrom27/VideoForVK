package ru.ikrom.repository

interface IRepository {
    suspend fun getPopularVideo(): List<VideoModel>
}
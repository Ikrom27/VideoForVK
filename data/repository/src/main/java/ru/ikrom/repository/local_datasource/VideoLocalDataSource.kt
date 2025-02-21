package ru.ikrom.repository.local_datasource

import androidx.room.withTransaction
import javax.inject.Inject

internal class VideoLocalDataSource @Inject constructor(
    private val videoDao: VideoDao,
    private val database: AppDatabase
) {
    fun getAllVideos(): List<VideoEntity> = videoDao.getAllVideos()

    suspend fun updateVideos(videos: List<VideoEntity>) {
        database.withTransaction {
            videoDao.clearVideos()
            videoDao.insertVideos(videos)
        }
    }

}

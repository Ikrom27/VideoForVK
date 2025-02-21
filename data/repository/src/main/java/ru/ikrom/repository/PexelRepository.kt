package ru.ikrom.repository

import ru.ikrom.repository.local_datasource.VideoLocalDataSource
import ru.ikrom.repository.local_datasource.toEntity
import ru.ikrom.repository.local_datasource.toModel
import ru.ikrom.repository.remote_datasource.PexelDataSource
import ru.ikrom.repository.remote_datasource.toModel
import ru.ikrom.video_usecase.IRepository
import ru.ikrom.video_usecase.models.ID
import ru.ikrom.video_usecase.models.VideoModel
import javax.inject.Inject

internal class PexelRepository @Inject constructor(
    private val remoteDataSource: PexelDataSource,
    private val localDataSource: VideoLocalDataSource
): IRepository {

    override suspend fun getPopularVideo(query: String): List<VideoModel> {
        return remoteDataSource.fetchPopularVideo(query).map { it.toModel() }.apply {
            if(isNotEmpty()) {
                localDataSource.updateVideos( map { it.toEntity() })
            }
        }
    }

    override suspend fun getSavedVideo(): List<VideoModel> {
        return localDataSource.getAllVideos().map { it.toModel() }
    }

    override suspend fun getVideo(id: ID): VideoModel {
        return remoteDataSource.getVideo(id.value.toInt()).toModel()
    }
}
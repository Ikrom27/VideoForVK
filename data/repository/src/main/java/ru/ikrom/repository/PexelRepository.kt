package ru.ikrom.repository

import ru.ikrom.repository.pexel_datasource.PexelDataSource
import ru.ikrom.repository.pexel_datasource.toModel
import javax.inject.Inject

class PexelRepository @Inject constructor(
    private val remoteDataSource: PexelDataSource
): IRepository {

    override suspend fun getPopularVideo(query: String): List<VideoModel> {
        return remoteDataSource.fetchPopularVideo(query).map { it.toModel() }
    }

    override suspend fun getVideo(id: ID): VideoModel {
        return remoteDataSource.getVideo(id.id).toModel()
    }
}
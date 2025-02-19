package ru.ikrom.repository

import ru.ikrom.repository.pexel_datasource.PexelDataSource
import javax.inject.Inject

class PexelRepository @Inject constructor(
    private val removeDataSource: PexelDataSource
): IRepository {

    override suspend fun getPopularVideo(): List<VideoModel> {
        return removeDataSource.fetchPopularVideo().map { VideoModel(
            id = it.id,
            title = "my video",
            thumbnailUrl = it.image,
            duration = it.duration,
            link = it.videoFiles.first().link
        ) }
    }
}
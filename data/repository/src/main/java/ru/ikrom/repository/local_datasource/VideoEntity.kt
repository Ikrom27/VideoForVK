package ru.ikrom.repository.local_datasource

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.ikrom.video_usecase.models.VideoModel

@Entity(tableName = "videos")
internal data class VideoEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "thumbnailUrl") val thumbnailUrl: String,
    @ColumnInfo(name = "duration") val duration: Int,
    @ColumnInfo(name = "link") val link: String
)

internal fun VideoModel.toEntity(): VideoEntity{
    return VideoEntity(
        id = id.value,
        title = title,
        thumbnailUrl = thumbnailUrl,
        duration = duration.seconds,
        link = link
    )
}
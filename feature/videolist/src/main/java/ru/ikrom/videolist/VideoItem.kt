package ru.ikrom.videolist

import android.net.Uri
import ru.ikrom.repository.ID

data class VideoItem(
    val id: ID,
    val title: String,
    val thumbnail: Uri,
    val duration: String,
)
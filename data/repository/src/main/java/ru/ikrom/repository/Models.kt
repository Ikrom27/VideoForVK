package ru.ikrom.repository

import android.net.Uri

data class VideoModel(
    val id: ID,
    val title: String,
    val thumbnailUrl: Uri,
    val duration: Duration,
    val link: Uri
)

@JvmInline
value class ID(val id: Int)
@JvmInline
value class Duration(private val seconds: Int) {
    val duration: String
        get() {
            val hours = seconds / 3600
            val minutes = (seconds % 3600) / 60
            val sec = seconds % 60
            return if (hours > 0) {
                String.format("%02d:%02d:%02d", hours, minutes, sec)
            } else {
                String.format("%02d:%02d", minutes, sec)
            }
        }

    override fun toString(): String = duration
}
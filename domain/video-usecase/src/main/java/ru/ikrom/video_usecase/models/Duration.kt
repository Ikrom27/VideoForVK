package ru.ikrom.video_usecase.models

@JvmInline
value class Duration(val seconds: Int) {
    val formatted: String
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

    override fun toString(): String = formatted
}
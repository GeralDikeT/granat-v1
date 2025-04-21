package com.example.granatv1.modules

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class GranatSong(
    val title: String,
    val artist: String,
    val albumTitle: String,
    val duration: Int,
    val path: String,
    val albumArt: Bitmap? = null,
    var isFavoriteState: Boolean = false
) {
    var isFavorite by mutableStateOf(isFavoriteState)
}

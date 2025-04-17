package com.example.granatv1.modules

import android.graphics.Bitmap

data class GranatSong(
    val title: String,
    val artist: String,
    val albumTitle: String,
    val duration: Int,
    val path: String,
    val albumArt: Bitmap? = null
) {

}

package com.example.granatv1.idk

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.provider.MediaStore
import android.util.Log
import java.io.File



data class SongInfo (
    val title: String,
    val artist: String,
    val albumTitle: String,
    val duration: String,
    val path: String,
    val albumArt: Bitmap?
    )


fun getAlbumArt(filePath: String) : Bitmap? {
    val retriever = MediaMetadataRetriever()
    return try {
        retriever.setDataSource(filePath)
        val art = retriever.embeddedPicture
        retriever.release()
        art?.let { BitmapFactory.decodeByteArray(it, 0, it.size) }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun getAllSongs(context: Context): List<SongInfo> {
    val songList = mutableListOf<SongInfo>()

    val projection = arrayOf(
        MediaStore.Audio.Media.TITLE,
        MediaStore.Audio.Media.ARTIST,
        MediaStore.Audio.Media.ALBUM,
        MediaStore.Audio.Media.DURATION,
        MediaStore.Audio.Media.DATA
    )

    val selection = "${MediaStore.Audio.Media.DATA} LIKE ?"
    val selectionArgs = arrayOf("%/Music/Drum%")

    val cursor: Cursor? = context.contentResolver.query(
        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
        projection,
        selection,
        selectionArgs,
        null
    )

    cursor?.use {
        val titleColumn = it.getColumnIndex(MediaStore.Audio.Media.TITLE)
        val artistColumn = it.getColumnIndex(MediaStore.Audio.Media.ARTIST)
        val albumColumn = it.getColumnIndex(MediaStore.Audio.Media.ALBUM)
        val durationColumn = it.getColumnIndex(MediaStore.Audio.Media.DURATION)
        val pathColumn = it.getColumnIndex(MediaStore.Audio.Media.DATA)

        while (it.moveToNext()) {
            val title = it.getString(titleColumn) ?: "Unknown Title"
            val artist = it.getString(artistColumn) ?: "Unknown Artist"
            val albumTitle = it.getString(albumColumn) ?: "Unknown Album"
            val duration = (it.getInt(durationColumn) / 1000).toString()
            val path = it.getString(pathColumn) ?: ""

            val albumArt = getAlbumArt(path)

            songList.add(SongInfo(title, artist, albumTitle, duration, path, albumArt))
        }
    }
    return songList
}

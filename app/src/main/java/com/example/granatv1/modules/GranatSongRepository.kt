package com.example.granatv1.modules

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.provider.MediaStore
import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

class GranatSongRepository {
    val list = mutableStateListOf<GranatSong>()

    fun loadSongs(context: Context) {
        list.clear()

        val projection = arrayOf(
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATA
        )

        val selection = "${MediaStore.Audio.Media.DATA} LIKE ?"
        val selectionArgs = arrayOf("%/Music/gugugaga%")

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
                val albumTitle = it.getString(albumColumn) ?: "Single"
                val duration = it.getInt(durationColumn)
                val path = it.getString(pathColumn) ?: ""

                list.add(GranatSong(title, artist, albumTitle, duration, path))
            }
        }
    }

    suspend fun loadAlbumArtsAsync() {
        withContext(Dispatchers.IO) {
            list.forEachIndexed { index, song ->
                val art = getAlbumArt(song.path)
                if (art != null) {
                    withContext(Dispatchers.Main) {
                        list[index] = song.copy(albumArt = art)
                    }
                }
            }
        }
    }

    fun getRandomSong(): GranatSong {
        return list[Random.nextInt(0, list.size)]
    }

    fun getSong(path: String): GranatSong? {
        return list.find { it.path == path }
    }

    private fun getAlbumArt(filePath: String): Bitmap? {
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
}

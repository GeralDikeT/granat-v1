package com.example.granatv1.modules

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import com.example.granatv1.MainActivity
import kotlin.random.Random

class GranatSongRepository() {
    val list = mutableListOf<GranatSong>();

    public fun loadSongs(context: Context) {
        val projection = arrayOf(
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATA
        )

        val cursor: Cursor? = context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )

        cursor?.use {
            val titleColumn = it.getColumnIndex(MediaStore.Audio.Media.TITLE)
            val artistColumn = it.getColumnIndex(MediaStore.Audio.Media.ARTIST)
            val albumColumn = it.getColumnIndex(MediaStore.Audio.Media.ALBUM)
            val durationColumn = it.getColumnIndex(MediaStore.Audio.Media.DURATION)
            val pathColumn = it.getColumnIndex(MediaStore.Audio.Media.DATA)

            while (it.moveToNext()) {
                val title = it.getString(titleColumn) ?: "Unknown Title";
                val artist = it.getString(artistColumn) ?: "Unknown Artist";
                val albumTitle = it.getString(albumColumn) ?: "Single";
                val duration = it.getInt(durationColumn);
                val path = it.getString(pathColumn) ?: "";

                //val albumArt = getAlbumArt(path)

                list.add(GranatSong(title, artist, albumTitle, duration, path));
            }
        }
    }

    fun getRandomSong(): GranatSong {
        return MainActivity.songs.list[Random.nextInt(0, MainActivity.songs.list.size)];
    }
    public fun getSong(path: String): GranatSong? {
        return null;
    }
}
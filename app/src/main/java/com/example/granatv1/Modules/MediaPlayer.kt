package com.example.granatv1.Modules

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.provider.MediaStore
import java.io.IOException
import kotlin.random.Random

class MediaPlayer(
    var mediaPlayer: MediaPlayer? = null,
    var currentPath: String? = null
)
{

        var currentSongIndex = 0

        fun playSong(path: String) {
            if (mediaPlayer == null || currentPath != path) {
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer().apply {
                    try {
                        setDataSource(path)
                        prepare()
                        start()
                        currentPath = path


                        setOnCompletionListener {
                            playNextSong()
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            } else {
                if (!mediaPlayer!!.isPlaying) {
                    mediaPlayer!!.start()
                }
            }
        }


    companion object {
        var songsList = mutableListOf<Song>()


        fun getAlbumArt(filePath: String): Bitmap? {
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

        fun getAllSongs(context: Context): List<Song> {
            val songList = mutableListOf<Song>()

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
                    val title = it.getString(titleColumn) ?: "Unknown Title"
                    val artist = it.getString(artistColumn) ?: "Unknown Artist"
                    val albumTitle = it.getString(albumColumn) ?: "Single"
                    val duration = (it.getInt(durationColumn) / 1000).toString()
                    val path = it.getString(pathColumn) ?: ""

                    val albumArt = getAlbumArt(path)

                    songList.add(Song(title, artist, albumTitle, duration, path, albumArt))
                }
            }
            songsList = songList

            return songList
        }
    }

        fun playNextSong() {

        }

        fun playPauseFunction() {
            if (mediaPlayer != null) {
                if (mediaPlayer!!.isPlaying) {
                    mediaPlayer!!.pause()
                } else {
                    mediaPlayer!!.start()
                }
            }
        }

        fun playRandomSong(context: Context) {
            if (songsList.isNotEmpty()) {
                val randomSong = songsList[Random.nextInt(songsList.size)]
                playSong(randomSong.path)
            } else {
                println("No songs available")
            }
        }
    }

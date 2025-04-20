package com.example.granatv1.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.granatv1.MainActivity
import com.example.granatv1.ui.SongPage

@Composable
fun SongList(
    modifier: Modifier = Modifier, onSongClick: () -> Unit) {
    val songs = remember { MainActivity.songs.list }

    Box(modifier.fillMaxWidth()) {
        LazyColumn {
            itemsIndexed(songs) { index, song ->
                SongItem(
                    granatSong = song,
                    modifier = Modifier.padding(horizontal = 2.dp),
                    onSongClick = { clickedSong ->
                        MainActivity.player.playSong(clickedSong)
                        onSongClick()
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

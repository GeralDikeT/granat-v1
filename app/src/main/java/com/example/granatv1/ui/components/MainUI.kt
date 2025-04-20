package com.example.granatv1.ui.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.granatv1.MainActivity
import com.example.granatv1.ui.theme.GranatV1Theme

@Composable
fun MainUI() {
    GranatV1Theme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(10.dp)
        ) {
            TopBar()
            HorizontalCardsList()
            Divider(modifier = Modifier.padding(top = 8.dp))
            UnderDivider()
            Spacer(modifier = Modifier.padding(top = 6.dp))

            Column(

            ) {
                SongList(modifier = Modifier.weight(1f))
                if (MainActivity.player.currentSong != null) {
                    BottomSongBar()
                }
            }
        }
    }
}

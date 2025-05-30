package com.example.granatv1.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.granatv1.MainActivity
import com.example.granatv1.R
import com.example.granatv1.ui.components.BottomSongBar
import com.example.granatv1.ui.components.HorizontalCardsList
import com.example.granatv1.ui.components.SongList
import com.example.granatv1.ui.components.TopBar
import com.example.granatv1.ui.components.UnderDivider
import com.example.granatv1.ui.theme.GranatV1Theme

@Composable
fun MainPage(navController: NavHostController) {
    GranatV1Theme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.black))
                .padding(10.dp)
        ) {
            TopBar()
            HorizontalCardsList()
            Divider(modifier = Modifier.padding(top = 8.dp))
            UnderDivider(navController)
            Spacer(modifier = Modifier.padding(top = 6.dp))

            Column {
                SongList(
                    modifier = Modifier.weight(1f),
                    onSongClick = {
                        navController.navigate("song")
                    }
                )
                if (MainActivity.player.currentSong != null) {
                    BottomSongBar(
                        onSongClick = {
                            navController.navigate("song")
                        }
                    )
                }
            }
        }
    }
}

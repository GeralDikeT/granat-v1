package com.example.granatv1
import com.example.granatv1.Modules.Song

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.granatv1.ui.theme.GranatV1Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material3.Divider
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import android.os.Build
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.content.ContextCompat
import android.Manifest
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.granatv1.Modules.MediaPlayer


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (isAudioPermissionGranted()) {
            accessAudioLibrary()
        } else {
            requestAudioPermission()
        }

        setContent {
            MainUI(this)
        }
    }
    private fun isAudioPermissionGranted() : Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED
        } else {
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun requestAudioPermission() {
        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(Manifest.permission.READ_MEDIA_AUDIO)
        } else {
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        requestPermissionLauncher.launch(permissions)
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {permission ->
        val audioPermissionGranted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permission[Manifest.permission.READ_MEDIA_AUDIO] ?: false
        } else {
            permission[Manifest.permission.READ_EXTERNAL_STORAGE] ?: false
        }

        if (audioPermissionGranted) {
            accessAudioLibrary()
        } else {

            Toast.makeText(this, "Audio Permission Granted", Toast.LENGTH_LONG).show()
        }
    }

    private  fun accessAudioLibrary() {
        return
    }

}

@Composable
fun MainUI(context: Context) {

    GranatV1Theme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(10.dp)
        ) {
            TopBar()
            Cards()
            Divider(modifier = Modifier.padding(top = 8.dp))
            UnderDivider()
            Spacer(modifier = Modifier.padding(top = 6.dp))

            SongListLoader(context)
        }
    }
}

@Composable
fun BottomSongBar(song: Song, modifier: Modifier = Modifier) {
    val isPlaying = remember(song) { mutableStateOf(true) }

    val artistAndAlbumInfo = song.artist + " | " + song.albumTitle

    Box(
        modifier
            .fillMaxWidth()
            .clickable() {
                println("ne skvoz")
            }
    ) {
        Box(
            modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color(0xFFE1D4D4), shape = RoundedCornerShape(8.dp))
                .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
                    .align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (song.albumArt != null) {
                        Image(
                            bitmap = song.albumArt.asImageBitmap(),
                            contentDescription = "Current song cover",
                            modifier = Modifier
                                .size(42.dp)
                                .clip(RoundedCornerShape(24.dp))
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.no_cover),
                            contentDescription = "Current song cover",
                            modifier = Modifier
                                .size(42.dp)
                                .clip(RoundedCornerShape(24.dp))
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column() {
                        Text(
                            text = song.title,
                            color = Color(0xFF924A4A),
                            fontSize = 16.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.widthIn(0.dp, 175.dp)
                        )
                        Text(
                            text = artistAndAlbumInfo,
                            color = Color.Gray,
                            fontSize = 14.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.widthIn(0.dp, 175.dp)
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(-8.dp)
                ) {
                    IconButton(onClick = { println("asdad") }) {
                        Image(
                            painter = painterResource(id = R.drawable.previussong_icon),
                            contentDescription = "previusSong",
                            modifier = Modifier
                                .size(25.dp)
                                .requiredWidth(25.dp)
                        )
                    }
                    IconButton(onClick = {
                        isPlaying.value = !isPlaying.value
                        MediaPlayer.playPauseFunction()
                    }) {
                        val icon = if (isPlaying.value) {
                            R.drawable.pause_icon
                        } else {
                            R.drawable.play_sign
                        }
                        Image(
                            painter = painterResource(id = icon),
                            contentDescription = "Play/Pause",
                            modifier = Modifier
                                .size(25.dp)
                                .requiredWidth(25.dp)
                        )
                    }
                    IconButton(onClick = { println("asdad") }) {
                        Image(
                            painter = painterResource(id = R.drawable.nextsong_icon),
                            contentDescription = "nextSong",
                            modifier = Modifier
                                .size(25.dp)
                                .requiredWidth(25.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SongListLoader(context: Context) {
    val songs = remember { MediaPlayer.getAllSongs(context).asReversed() }
    var currentSong by remember { mutableStateOf<Song?>(null) }

    Box(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(
            modifier = Modifier.padding(bottom = if (currentSong != null) 60.dp else 0.dp)
        ) {
            itemsIndexed(songs) { index, song ->
                SongItem(
                    song = song,
                    modifier = Modifier.padding(horizontal = 2.dp),
                    onSongClick = { clickedSong ->
                        MediaPlayer.playSong()
                        currentSong = clickedSong
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        currentSong?.let { song ->
            BottomSongBar(
                song = song,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(33.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .fillMaxHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 7.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Searching Music, Playlists & Artists",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 17.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(
            onClick = { println("Settings clicked") },
            modifier = Modifier
                .size(28.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.settings_icon),
                contentDescription = "Settings",
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Composable
fun HorizontalCardButton(header: String, imageId: Int, modifier: Modifier = Modifier) {
    Box(modifier) {
        Box(modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(9.dp))
            .fillMaxHeight()
            .fillMaxWidth()
            .clickable() {
                println("Box Clicked")
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 8.dp, top = 8.dp, bottom = 3.dp),
            ) {

                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = "Settings",
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = header,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun Cards() {

    Row(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .height(55.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HorizontalCardButton("Favorite", R.drawable.favorite_icon, Modifier.weight(1f))
        HorizontalCardButton("Playlists", R.drawable.playlist_icon, Modifier.weight(1f))
        HorizontalCardButton("Soon", R.drawable.question_sign_icon, Modifier.weight(1f))
    }
}

@Composable
fun UnderDivider() {
    var context = LocalContext.current
    Row(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .height(33.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Row(modifier = Modifier.weight(1f)
            .clickable() {
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.play_button_icon),
                contentDescription = "PlayInRandom",
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "In random order",
                color = Color.White,
                fontSize = 14.sp
            )
        }

        IconButton(onClick = { println("FilterButtonClicked") },
            modifier = Modifier
                .size(28.dp))
        {
            Image(
                painter = painterResource(id = R.drawable.filter_icon),
                contentDescription = "Filter",
                modifier = Modifier.size(28.dp)
            )
        }
    }

}

@Composable
fun SongItem(song: Song, modifier: Modifier, onSongClick: (Song) -> Unit) {
    val artistAndAlbumInfo = song.artist + " | " + song.albumTitle

    val shortTitle = if (song.title.length > 15) song.title.take(15) + "..." else song.title
    val shortArtistAndAlbumInfo = if (artistAndAlbumInfo.length > 15) song.artist.take(15) + "..." else song.artist
    Box(modifier
        .clickable() {
            onSongClick(song)
        }) {
        Box(
            modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(4.dp))
                .padding(6.dp)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(

                ) {
                    if (song.albumArt != null) {
                        Image(
                            bitmap = song.albumArt.asImageBitmap(),
                            contentDescription = "songCover",
                            modifier = Modifier
                                .size(48.dp)
                                .clip(shape = RoundedCornerShape(4.dp))
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.no_cover),
                            contentDescription = "songCover",
                            modifier = Modifier
                                .size(48.dp)
                                .clip(shape = RoundedCornerShape(4.dp))
                        )
                    }
                }
                Spacer(modifier = Modifier.width(6.dp))
                Column(
                    modifier.weight(1f)
                ) {
                    Text(
                        text = shortTitle,
                        color = Color.Black,
                        fontSize = 18.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        modifier.height(22.dp).width(IntrinsicSize.Max)
                    ) {
                        Text(
                            text = durationCalculate(song.duration),
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                        Spacer(modifier.width(4.dp))
                        Text(
                            text = shortArtistAndAlbumInfo,
                            fontSize = 14.sp,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                Box(

                ) {
                    IconButton(onClick = { println("SongSettingsClicked") },
                        modifier = Modifier
                            .size(28.dp))
                    {
                        Image(
                            painter = painterResource(id = R.drawable.dots_icon),
                            contentDescription = "SongSettings",
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }
        }
    }
}

fun durationCalculate(duration: String) : String {
    val totalSeconds = duration.toInt()
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    var context = LocalContext.current
    MainUI(context)
}
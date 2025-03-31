package com.example.granatv1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.granatv1.ui.theme.GranatV1Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Divider
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainUI()
        }
    }
}

@Composable
fun MainUI() {
    GranatV1Theme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(8.dp)
        ) {
            TopBar()
            Cards()

            Divider(modifier = Modifier.padding(top = 8.dp))

            UnderDivider()
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
                println("clickedRandomButton")
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



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainUI()
}
package com.example.granatv1.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.compose.animation.*
import androidx.compose.animation.core.tween

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainPage(navController)
        }

        composable(
            "song",
            enterTransition = {
                slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight },
                    animationSpec = tween(durationMillis = 650)
                )
            },
            popExitTransition = {
                slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight },
                    animationSpec = tween(durationMillis = 650)
                )
            }
        ) {
            SongPage(navController)
        }
    }
}

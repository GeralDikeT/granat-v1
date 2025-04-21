package com.example.granatv1.modules

import android.content.Context
import android.content.SharedPreferences

fun saveFavoriteStatus(context: Context, songPath: String, isFavorite: Boolean) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putBoolean(songPath, isFavorite)
    editor.apply()
}

fun loadFavoriteStatus(context: Context, songPath: String): Boolean {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean(songPath, false)
}
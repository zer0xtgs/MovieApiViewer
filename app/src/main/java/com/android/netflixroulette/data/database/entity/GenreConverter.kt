package com.android.netflixroulette.data.database.entity

import androidx.room.TypeConverter
import com.android.netflixroulette.data.database.entity.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreConverter {

    @TypeConverter
    fun fromGenreList(value: List<Genre>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Genre>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toGenreList(value: String): List<Genre> {
        val gson = Gson()
        val type = object : TypeToken<List<Genre>>() {}.type
        return gson.fromJson(value, type)
    }
}
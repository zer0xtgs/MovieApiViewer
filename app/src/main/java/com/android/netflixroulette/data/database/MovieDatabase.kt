package com.android.netflixroulette.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.netflixroulette.data.database.entity.DetailMovieResponse
import com.android.netflixroulette.data.database.entity.GenreConverter

@Database(
    entities = [DetailMovieResponse::class],
    version = 1
)

@TypeConverters(GenreConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile private var instance: MovieDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(
            LOCK
        ) {
            instance
                ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                MovieDatabase::class.java, "movies.db")
                .build()
    }

}
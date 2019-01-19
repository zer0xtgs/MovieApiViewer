package com.android.netflixroulette.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.netflixroulette.data.database.entity.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movieEntry: Movie)

    @Query("SELECT * FROM saved_movies")
    fun getSavedMoviesList(): LiveData<List<Movie>>

}
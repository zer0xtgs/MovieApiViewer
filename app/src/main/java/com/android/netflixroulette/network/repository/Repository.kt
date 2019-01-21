package com.android.netflixroulette.network.repository

import androidx.lifecycle.LiveData
import com.android.netflixroulette.data.database.entity.Movie
import com.android.netflixroulette.network.response.MovieResponse

interface Repository {

    fun getSearchByTitleResponse() : LiveData<MovieResponse>

    // network
    suspend fun getMovieByTitleList(title: String)

    // db
    fun saveMovie(movie: Movie)
    fun getSavedMoviesList(): LiveData<List<Movie>>
}
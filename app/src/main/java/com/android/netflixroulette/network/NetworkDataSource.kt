package com.android.netflixroulette.network

import androidx.lifecycle.LiveData
import com.android.netflixroulette.network.response.MovieResponse

interface NetworkDataSource {
    val downloadedMovieResponse: LiveData<MovieResponse>

    suspend fun fetchMoviesByTitle(title: String)

}
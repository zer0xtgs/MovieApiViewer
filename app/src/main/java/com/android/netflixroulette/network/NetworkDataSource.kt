package com.android.netflixroulette.network

import androidx.lifecycle.LiveData
import com.android.netflixroulette.network.response.DirectorResponse
import com.android.netflixroulette.network.response.MovieResponse
import com.android.netflixroulette.network.response.MoviesByDirectorResponse

interface NetworkDataSource {
    val downloadedMoviesResponse: LiveData<MovieResponse>
    val downloadedDirectorResponse: LiveData<DirectorResponse>
    val downloadedMoviesByDirectorResponse: LiveData<MoviesByDirectorResponse>

    suspend fun fetchMoviesByTitle(title: String)
    suspend fun fetchMoviesByDirector(id: Long)
    suspend fun fetchDirector(title: String)
}
package com.android.netflixroulette.network.repository

import androidx.lifecycle.LiveData
import com.android.netflixroulette.data.database.entity.Movie
import com.android.netflixroulette.network.response.DirectorResponse
import com.android.netflixroulette.network.response.MovieResponse
import com.android.netflixroulette.network.response.MoviesByDirectorResponse

interface Repository {

    fun getSearchByTitleResponse() : LiveData<MovieResponse>
    fun getSearchByDirectorResponse() : LiveData<MoviesByDirectorResponse>
    fun getDirectorResponse() : LiveData<DirectorResponse>

    // network
    suspend fun getMovieByTitleList(title: String)
    suspend fun getMovieByDirectorList(id: Long)
    suspend fun getDirector(name: String)

    // db
    fun saveMovie(movie: Movie)
    fun getSavedMoviesList(): LiveData<List<Movie>>
}
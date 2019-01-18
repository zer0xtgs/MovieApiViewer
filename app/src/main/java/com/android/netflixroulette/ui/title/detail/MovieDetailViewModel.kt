package com.android.netflixroulette.ui.title.detail

import androidx.lifecycle.ViewModel
import com.android.netflixroulette.data.database.entity.DetailMovieResponse
import com.android.netflixroulette.network.repository.Repository

class MovieDetailViewModel(private val repository: Repository) : ViewModel() {

    val detailMovieResponse = repository.getDetailMovieInfoResponse()

    suspend fun getDetailMovieInfo(id: Long) = repository.getDetailMovieInfo(id)

    fun persistDetailMovie(movie : DetailMovieResponse) = repository.persistDetailMovie(movie)

}

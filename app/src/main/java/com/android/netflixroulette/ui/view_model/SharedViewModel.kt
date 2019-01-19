package com.android.netflixroulette.ui.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.android.netflixroulette.data.database.entity.Movie
import com.android.netflixroulette.network.repository.Repository

class SharedViewModel(
    private val repository: Repository
) : ViewModel() {

    val savedMoviesList = repository.getSavedMoviesList()
    val selectedMovie = MutableLiveData<Movie>()

    fun saveMovie(movie: Movie) = repository.saveMovie(movie)

    fun setSelectedMovie(movie: Movie) {
        selectedMovie.value = movie
    }

}

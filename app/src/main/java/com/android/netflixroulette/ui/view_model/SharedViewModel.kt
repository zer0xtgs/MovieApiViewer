package com.android.netflixroulette.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.netflixroulette.data.database.entity.Movie
import com.android.netflixroulette.network.repository.Repository
import com.android.netflixroulette.network.response.DirectorResponse
import com.android.netflixroulette.network.response.MovieResponse
import com.android.netflixroulette.network.response.MoviesByDirectorResponse

class SharedViewModel(
    private val repository: Repository
) : ViewModel() {

    val getMovieByDirectorResponse: LiveData<MoviesByDirectorResponse>
        get() = repository.getSearchByDirectorResponse()

    val searchDirectorResponse: LiveData<DirectorResponse>
        get() = repository.getDirectorResponse()

    val searchByTitleResponse: LiveData<MovieResponse>
        get() = repository.getSearchByTitleResponse()

    val savedMoviesList: LiveData<List<Movie>>
        get() = repository.getSavedMoviesList()

    val selectedMovie = MutableLiveData<Movie>()

    suspend fun getDirector(name: String) = repository.getDirector(name)

    suspend fun getMovieByDirectorList(id : Long) = repository.getMovieByDirectorList(id)

    suspend fun getMovieByTitle(title: String) = repository.getMovieByTitleList(title)

    fun saveMovie(movie: Movie) = repository.saveMovie(movie)

    fun setSelectedMovie(movie: Movie) {
        selectedMovie.value = movie
    }
}

package com.android.netflixroulette.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.netflixroulette.network.response.DirectorResponse
import com.android.netflixroulette.network.response.MovieResponse
import com.android.netflixroulette.network.response.MoviesByDirectorResponse

class NetworkDataSourceImpl(
    private val theMovieDBApiService: TheMovieDBApiService
) : NetworkDataSource {

    private val _downloadedMovieResponse = MutableLiveData<MovieResponse>()
    override val downloadedMoviesResponse: LiveData<MovieResponse>
        get() = _downloadedMovieResponse

    override suspend fun fetchMoviesByTitle(title: String) {
        try {
            //TODO debug
            Log.d("Network", "fetchMoviesByTitle called")
            val fetchedMoviesByTitle = theMovieDBApiService
                .getMovieByTitle(title)
                .await()

            _downloadedMovieResponse.postValue(fetchedMoviesByTitle)

            //TODO implement NoConnectivityException
        } catch (e: Exception) {
            Log.e("Connectivity", "No internet connection")
        }
    }

    private val _downloadedDirectorResponse = MutableLiveData<DirectorResponse>()
    override val downloadedDirectorResponse: LiveData<DirectorResponse>
        get() = _downloadedDirectorResponse

    override suspend fun fetchDirector(name: String) {
        try {
            //TODO debug
            Log.d("Network", "fetchMoviesByTitle called")
            val fetchedDirectors = theMovieDBApiService
                .getDirector(name)
                .await()

            _downloadedDirectorResponse.postValue(fetchedDirectors)

            //TODO implement NoConnectivityException
        } catch (e: Exception) {
            Log.e("Connectivity", "No internet connection")
        }
    }

    private val _downloadedMoviesByDirectorResponse = MutableLiveData<MoviesByDirectorResponse>()
    override val downloadedMoviesByDirectorResponse: LiveData<MoviesByDirectorResponse>
        get() = _downloadedMoviesByDirectorResponse

    override suspend fun fetchMoviesByDirector(id: Long) {
        try {
            val fetchedMoviesByDirector = theMovieDBApiService
                .getMovieByDirector(id)
                .await()

            _downloadedMoviesByDirectorResponse.postValue(fetchedMoviesByDirector)
        } catch (e: Exception) {
            Log.e("Connectivity", "No internet connection")
        }
    }


}
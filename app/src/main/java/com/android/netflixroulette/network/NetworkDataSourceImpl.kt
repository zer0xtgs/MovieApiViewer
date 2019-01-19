package com.android.netflixroulette.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.netflixroulette.network.response.MovieResponse

class NetworkDataSourceImpl(
    private val theMovieDBApiService: TheMovieDBApiService
) : NetworkDataSource {

    private val _downloadedMovieResponse = MutableLiveData<MovieResponse>()
    override val downloadedMovieResponse: LiveData<MovieResponse>
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
}
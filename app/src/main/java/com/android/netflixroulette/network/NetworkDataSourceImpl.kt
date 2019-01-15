package com.android.netflixroulette.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.netflixroulette.network.response.SearchByTitleResponse

class NetworkDataSourceImpl(
    private val theMovieDBApiService: TheMovieDBApiService) : NetworkDataSource {

    private val _downloadedSearchByTitleResponse = MutableLiveData<SearchByTitleResponse>()
    override val downloadedSearchByTitleResponse: LiveData<SearchByTitleResponse>
        get() = _downloadedSearchByTitleResponse

    override suspend fun fetchMovieByTitle(title: String) {
        try {
            val fetchedMovieByTitle = theMovieDBApiService
                .getMovieByTitle(title)
                .await()

                _downloadedSearchByTitleResponse.postValue(fetchedMovieByTitle)

            //TODO implement NoConnectivityException
        } catch (e: Exception){
            Log.e("Connectivity", "No internet connection")
        }
    }
}
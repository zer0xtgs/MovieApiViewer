package com.android.netflixroulette.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.netflixroulette.network.response.DetailMovieResponse
import com.android.netflixroulette.network.response.SearchByTitleResponse

class NetworkDataSourceImpl(
    private val theMovieDBApiService: TheMovieDBApiService) : NetworkDataSource {

    private val _downloadedSearchByTitleResponse = MutableLiveData<SearchByTitleResponse>()
    override val downloadedSearchByTitleResponse: LiveData<SearchByTitleResponse>
        get() = _downloadedSearchByTitleResponse

    override suspend fun fetchMoviesByTitle(title: String) {
        try {
            val fetchedMoviesByTitle = theMovieDBApiService
                .getMovieByTitle(title)
                .await()

                _downloadedSearchByTitleResponse.postValue(fetchedMoviesByTitle)

            //TODO implement NoConnectivityException
        } catch (e: Exception){
            Log.e("Connectivity", "No internet connection")
        }
    }

    private val _downloadedDetailMovieResponse = MutableLiveData<DetailMovieResponse>()
    override val downloadedDetailMovieResponse: LiveData<DetailMovieResponse>
        get() = _downloadedDetailMovieResponse

    override suspend fun fetchMovieDetailInfo(id: Long) {
        try {
            val fetchedDetailMovieInfo = theMovieDBApiService
                .getMovieInfo(id)
                .await()

            _downloadedDetailMovieResponse.postValue(fetchedDetailMovieInfo)

            //TODO implement NoConnectivityException
        } catch (e: Exception){
            Log.e("Connectivity", "No internet connection")
        }
    }


}
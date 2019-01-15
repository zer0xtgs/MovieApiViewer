package com.android.netflixroulette.network

import androidx.lifecycle.LiveData
import com.android.netflixroulette.network.response.SearchByTitleResponse

interface NetworkDataSource {
    val downloadedSearchByTitleResponse: LiveData<SearchByTitleResponse>

    suspend fun fetchMovieByTitle(title : String)
}
package com.android.netflixroulette.network

import androidx.lifecycle.LiveData
import com.android.netflixroulette.data.db.entity.DetailMovieResponse
import com.android.netflixroulette.network.response.SearchByTitleResponse

interface NetworkDataSource {
    val downloadedSearchByTitleResponse: LiveData<SearchByTitleResponse>
    val downloadedDetailMovieResponse: LiveData<DetailMovieResponse>

    suspend fun fetchMoviesByTitle(title : String)
    suspend fun fetchMovieDetailInfo(id : Long)
}
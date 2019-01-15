package com.android.netflixroulette.network.repository

import androidx.lifecycle.LiveData
import com.android.netflixroulette.network.NetworkDataSource
import com.android.netflixroulette.network.response.SearchByTitleResponse

class RepositoryImpl(
    private val networkDataSource: NetworkDataSource
) : Repository {

    override suspend fun getMovieByTitleList(title: String): LiveData<SearchByTitleResponse> {
        networkDataSource.fetchMovieByTitle(title)
        return networkDataSource.downloadedSearchByTitleResponse
    }

}
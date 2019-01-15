package data.network

import androidx.lifecycle.LiveData
import data.network.response.SearchByTitleResponse

interface NetworkDataSource {
    val downloadedSearchByTitleResponse: LiveData<SearchByTitleResponse>

    suspend fun fetchMovieByTitle(title : String)
}
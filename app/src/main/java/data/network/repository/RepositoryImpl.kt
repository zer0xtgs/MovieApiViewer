package data.network.repository

import androidx.lifecycle.LiveData
import data.network.NetworkDataSource
import data.network.response.SearchByTitleResponse

class RepositoryImpl(
    private val networkDataSource: NetworkDataSource
) : Repository {

    override suspend fun getMovieByTitleList(title: String): LiveData<SearchByTitleResponse> {
        networkDataSource.fetchMovieByTitle(title)
        return networkDataSource.downloadedSearchByTitleResponse
    }

}
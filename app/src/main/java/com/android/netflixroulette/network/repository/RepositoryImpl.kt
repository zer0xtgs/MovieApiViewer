package com.android.netflixroulette.network.repository

import androidx.lifecycle.LiveData
import com.android.netflixroulette.data.database.MovieDao
import com.android.netflixroulette.data.database.entity.DetailMovieResponse
import com.android.netflixroulette.network.NetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    private val movieDao: MovieDao
) : Repository {

    private val searchByTitleResponse = networkDataSource.downloadedSearchByTitleResponse
    private val detailMovieResponse = networkDataSource.downloadedDetailMovieResponse

    override fun getMovieByTitleResponse() = searchByTitleResponse
    override fun getDetailMovieInfoResponse() = detailMovieResponse

    override suspend fun getMovieByTitleList(title: String) =
        networkDataSource.fetchMoviesByTitle(title)

    override suspend fun getDetailMovieInfo(id: Long) =
        networkDataSource.fetchMovieDetailInfo(id)

    override fun persistDetailMovie(detailMovieInfo: DetailMovieResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            movieDao.saveMovie(detailMovieInfo)
        }
    }

    override fun getSavedMoviesList(): LiveData<List<DetailMovieResponse>> {
        return movieDao.getSavedMoviesList()
    }
}
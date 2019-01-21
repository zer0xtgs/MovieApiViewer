package com.android.netflixroulette.network.repository

import androidx.lifecycle.LiveData
import com.android.netflixroulette.data.database.MovieDao
import com.android.netflixroulette.data.database.entity.Movie
import com.android.netflixroulette.network.NetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    private val movieDao: MovieDao
) : Repository {

    override fun getSearchByTitleResponse() = networkDataSource.downloadedMovieResponse

    override suspend fun getMovieByTitleList(title: String) {
        networkDataSource.fetchMoviesByTitle(title)
    }

    override fun saveMovie(movie: Movie) {
        GlobalScope.launch(Dispatchers.IO) {
            movieDao.saveMovie(movie)
        }
    }

    override fun getSavedMoviesList(): LiveData<List<Movie>> = movieDao.getSavedMoviesList()
}


//    private val searchByTitleResponse = networkDataSource.downloadedMovieResponse
//    private val detailMovieResponse = networkDataSource.downloadedDetailMovieResponse
//
//    override fun getMovieByTitleResponse() = searchByTitleResponse
//    override fun getDetailMovieInfoResponse() = detailMovieResponse
//
//    override suspend fun getMovieByTitleList(title: String) =
//        networkDataSource.fetchMoviesByTitle(title)
//
//    override suspend fun getDetailMovieInfo(id: Long) =
//        networkDataSource.fetchMovieDetailInfo(id)
//
//    override fun persistDetailMovie(detailMovieInfo: DetailMovieResponse) {
//        GlobalScope.launch(Dispatchers.IO) {
//            movieDao.saveMovie(detailMovieInfo)
//        }
//    }
//
//    override fun getSavedMoviesList(): LiveData<List<DetailMovieResponse>> {
//        return movieDao.getSavedMoviesList()
//    }
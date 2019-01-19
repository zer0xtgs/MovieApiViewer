package com.android.netflixroulette.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.netflixroulette.data.database.MovieDao
import com.android.netflixroulette.data.database.entity.Movie
import com.android.netflixroulette.network.NetworkDataSource
import com.android.netflixroulette.network.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    private val movieDao: MovieDao
) : Repository {

    private val _searchByTitleResponse = MutableLiveData<MovieResponse>()
    override val searchByTitleResponse: LiveData<MovieResponse>
        get() = _searchByTitleResponse

    override suspend fun getMovieByTitleList(title: String) {
        networkDataSource.fetchMoviesByTitle(title)
        _searchByTitleResponse.postValue(networkDataSource.downloadedMovieResponse.value)
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
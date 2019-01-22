package com.android.netflixroulette.network.repository

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

//    map { it }.filter { it.job.equals("director", true) }

    override fun getSearchByTitleResponse() =
        networkDataSource.downloadedMoviesResponse

    override fun getSearchByDirectorResponse() =
        networkDataSource.downloadedMoviesByDirectorResponse

    override fun getDirectorResponse() =
        networkDataSource.downloadedDirectorResponse

    override suspend fun getMovieByTitleList(title: String) =
        networkDataSource.fetchMoviesByTitle(title)

    override suspend fun getMovieByDirectorList(id: Long) =
        networkDataSource.fetchMoviesByDirector(id)

    override suspend fun getDirector(name: String) =
        networkDataSource.fetchDirector(name)

    override fun saveMovie(movie: Movie) {
        GlobalScope.launch(Dispatchers.IO) {
            movieDao.saveMovie(movie)
        }
    }

    override fun getSavedMoviesList() =
        movieDao.getSavedMoviesList()
}
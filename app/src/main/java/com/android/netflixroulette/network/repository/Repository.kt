package com.android.netflixroulette.network.repository

import androidx.lifecycle.LiveData
import com.android.netflixroulette.data.database.entity.DetailMovieResponse
import com.android.netflixroulette.network.response.SearchByTitleResponse

interface Repository {
    suspend fun getMovieByTitleList(title : String)
    suspend fun getDetailMovieInfo(id : Long)

    fun getMovieByTitleResponse() : LiveData<SearchByTitleResponse>
    fun getDetailMovieInfoResponse() : LiveData<DetailMovieResponse>

    fun persistDetailMovie(detailMovieInfo : DetailMovieResponse)
    fun getSavedMoviesList() : LiveData<List<DetailMovieResponse>>
}
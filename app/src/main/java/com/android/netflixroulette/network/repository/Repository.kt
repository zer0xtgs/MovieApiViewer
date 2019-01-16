package com.android.netflixroulette.network.repository

import androidx.lifecycle.LiveData
import com.android.netflixroulette.data.db.entity.DetailMovieResponse
import com.android.netflixroulette.network.response.SearchByTitleResponse

interface Repository {
    suspend fun getMovieByTitleList(title : String) : LiveData<SearchByTitleResponse>
    suspend fun getDetailMovieInfo(id : Long) : LiveData<DetailMovieResponse>
}
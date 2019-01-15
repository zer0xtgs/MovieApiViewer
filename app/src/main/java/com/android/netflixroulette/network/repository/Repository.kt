package com.android.netflixroulette.network.repository

import androidx.lifecycle.LiveData
import com.android.netflixroulette.network.response.SearchByTitleResponse

interface Repository {
    suspend fun getMovieByTitleList(title : String) : LiveData<SearchByTitleResponse>
}
package data.network.repository

import androidx.lifecycle.LiveData
import data.network.response.SearchByTitleResponse

interface Repository {
    suspend fun getMovieByTitleList(title : String) : LiveData<SearchByTitleResponse>
}
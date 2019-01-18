package com.android.netflixroulette.ui.title.list

import androidx.lifecycle.ViewModel
import com.android.netflixroulette.network.repository.Repository

class SearchByTitleListViewModel(private val repository: Repository) : ViewModel() {

     val searchByTitleEntries = repository.getMovieByTitleResponse()

     suspend fun getMovieByTitle(title: String) = repository.getMovieByTitleList(title)
}

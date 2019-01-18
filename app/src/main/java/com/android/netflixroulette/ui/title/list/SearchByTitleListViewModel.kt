package com.android.netflixroulette.ui.title.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.netflixroulette.network.repository.Repository
import com.android.netflixroulette.network.response.SearchByTitleResponse

class SearchByTitleListViewModel(private val repository: Repository) : ViewModel() {

    private var _searchByTitleEntries = MutableLiveData<SearchByTitleResponse>()
    val searchByTitleEntries: LiveData<SearchByTitleResponse>
        get() = _searchByTitleEntries

    suspend fun getMovieByTitle(title: String) {
        val searchByTitleEntries =
            repository.getMovieByTitleList(title)

        // is it okay?
        _searchByTitleEntries = searchByTitleEntries as MutableLiveData<SearchByTitleResponse>
    }
}

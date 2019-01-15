package com.android.netflixroulette.ui.title.list

import androidx.lifecycle.ViewModel
import com.android.netflixroulette.internal.lazyDeferred
import com.android.netflixroulette.network.repository.Repository

class SearchByTitleListViewModel (repository: Repository) : ViewModel() {

    val searchByTitleEntries by lazyDeferred {
        repository.getMovieByTitleList("Aquaman")
    }
}

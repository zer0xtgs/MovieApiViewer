package com.android.netflixroulette.ui.title.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.netflixroulette.data.database.entity.DetailMovieResponse
import com.android.netflixroulette.network.repository.Repository

class MovieDetailViewModel(private val repository: Repository) : ViewModel() {

    private var _detailMovieResponse = MutableLiveData<DetailMovieResponse>()
    val detailMovieResponse: LiveData<DetailMovieResponse>
        get() = _detailMovieResponse

    suspend fun getDetailMovieInfo(id: Long) {
        val detailMovieInfo =
            repository.getDetailMovieInfo(id)

        // is it okay?
        _detailMovieResponse = detailMovieInfo as MutableLiveData<DetailMovieResponse>
    }
}

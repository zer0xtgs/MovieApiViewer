package com.android.netflixroulette.ui.title.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.netflixroulette.internal.lazyDeferred
import com.android.netflixroulette.network.repository.Repository
import com.android.netflixroulette.data.db.entity.DetailMovieResponse

class MovieDetailViewModel(private val repository: Repository) : ViewModel() {

    private val _detailMovieInfo = MutableLiveData<DetailMovieResponse>()
    val detailMovieInfo: LiveData<DetailMovieResponse>
        get() = _detailMovieInfo

    suspend fun getDetailMovieInfo(id: Long) {
        val detailMovieInfo by lazyDeferred {
            repository.getDetailMovieInfo(id)
        }
        _detailMovieInfo.value = detailMovieInfo.await().value
    }
}

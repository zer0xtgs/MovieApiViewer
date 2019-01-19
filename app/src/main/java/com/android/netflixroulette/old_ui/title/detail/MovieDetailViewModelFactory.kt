//package com.android.netflixroulette.ui.title.detail
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.android.netflixroulette.network.repository.Repository
//
//class MovieDetailViewModelFactory(
//    private val repository: Repository
//) : ViewModelProvider.NewInstanceFactory() {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return MovieDetailViewModel(repository) as T
//    }
//}
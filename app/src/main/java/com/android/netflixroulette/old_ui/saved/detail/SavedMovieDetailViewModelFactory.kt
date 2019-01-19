//package com.android.netflixroulette.ui.saved.detail
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.android.netflixroulette.network.repository.Repository
//
//class SavedMovieDetailViewModelFactory(
//    private val repository: Repository
//) : ViewModelProvider.NewInstanceFactory() {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return SavedMovieDetailViewModel(repository) as T
//    }
//}
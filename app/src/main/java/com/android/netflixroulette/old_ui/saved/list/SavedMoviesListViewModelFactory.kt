//package com.android.netflixroulette.ui.saved.list
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.android.netflixroulette.network.repository.Repository
//
//class SavedMoviesListViewModelFactory(
//    private val repository: Repository
//) : ViewModelProvider.NewInstanceFactory() {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return SavedMoviesListViewModel(repository) as T
//    }
//}
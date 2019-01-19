//package com.android.netflixroulette.ui.title.list
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.android.netflixroulette.network.repository.Repository
//
//class SearchByTitleListViewModelFactory(
//    private val repository: Repository
//) : ViewModelProvider.NewInstanceFactory() {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return SearchByTitleListViewModel(repository) as T
//    }
//}
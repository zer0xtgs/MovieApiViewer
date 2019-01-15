package ui.title.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import data.network.repository.Repository

class SearchWithTitleListViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchWithTitleListViewModel(repository) as T
    }
}
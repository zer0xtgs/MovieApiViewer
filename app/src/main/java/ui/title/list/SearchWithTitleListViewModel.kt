package ui.title.list

import androidx.lifecycle.ViewModel;
import data.network.repository.Repository
import internal.lazyDeferred

class SearchWithTitleListViewModel (repository: Repository) : ViewModel() {

    val SearchByTitleEntryes by lazyDeferred {
        repository.getMovieByTitleList("Aquaman")
    }
}

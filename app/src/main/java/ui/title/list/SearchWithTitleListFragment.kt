package ui.title.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.netflixroulette.R
import data.network.NetworkDataSourceImpl
import data.network.TheMovieDBApiService
import data.network.repository.RepositoryImpl
import kotlinx.android.synthetic.main.search_with_title_list_fragment.*
import kotlinx.coroutines.launch
import ui.base.ScopedFragment

class SearchWithTitleListFragment : ScopedFragment() {

    private val apiService = TheMovieDBApiService()
    private val networkDataSource = NetworkDataSourceImpl(apiService)
    private val repository = RepositoryImpl(networkDataSource)
    private val viewModelFactory = SearchWithTitleListViewModelFactory(repository)
    private lateinit var viewModel: SearchWithTitleListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_with_title_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(SearchWithTitleListViewModel::class.java)

        bindUI()
    }

    private fun bindUI() = launch {
        val searchByTitleList = viewModel.SearchByTitleEntryes.await()

        recyclerView.adapter = SearchByTitleEntryAdapter(emptyList())

        searchByTitleList.observe(this@SearchWithTitleListFragment, Observer {
            if (it == null) {
                return@Observer
            } else {
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(this@SearchWithTitleListFragment.context)
                    adapter = SearchByTitleEntryAdapter(it.entries)
                }
            }
        })
    }

}

package com.android.netflixroulette.ui.title.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.netflixroulette.R
import com.android.netflixroulette.network.NetworkDataSourceImpl
import com.android.netflixroulette.network.TheMovieDBApiService
import com.android.netflixroulette.network.repository.RepositoryImpl
import com.android.netflixroulette.network.response.SearchByTitleEntry
import com.android.netflixroulette.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.search_with_title_list_fragment.*
import kotlinx.coroutines.launch

class SearchByTitleListFragment : ScopedFragment(), SearchByTitleEntryAdapter.Listener {

    private val apiService = TheMovieDBApiService()
    private val networkDataSource = NetworkDataSourceImpl(apiService)
    private val repository = RepositoryImpl(networkDataSource)
    private val viewModelFactory = SearchByTitleListViewModelFactory(repository)
    private lateinit var viewModel: SearchByTitleListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_with_title_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(SearchByTitleListViewModel::class.java)

        bindUI()
    }

    private fun bindUI() = launch {
        //todo take Title form input text
        viewModel.getMovieByTitle("Aquaman")

        viewModel.searchByTitleEntries.observe(this@SearchByTitleListFragment, Observer {
            if (it == null) return@Observer

            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@SearchByTitleListFragment.context)
                adapter = SearchByTitleEntryAdapter(it.entries, this@SearchByTitleListFragment)
            }
        })
    }

    override fun onClick(item: SearchByTitleEntry) {
        this.findNavController().navigate(
            SearchByTitleListFragmentDirections.actionSearchByTitleToMovieDetail(item.id)
        )
    }
}

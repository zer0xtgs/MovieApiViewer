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
import com.android.netflixroulette.network.response.SearchByTitleEntry
import com.android.netflixroulette.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.search_with_title_list_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SearchByTitleListFragment : ScopedFragment(), KodeinAware, SearchByTitleEntryAdapter.Listener {

    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: SearchByTitleListViewModelFactory by instance()

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
        setSearchLisener()

        val searchByTitleResponse = viewModel.searchByTitleEntries

        searchByTitleResponse.observe(this@SearchByTitleListFragment, Observer {
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

    private fun setSearchLisener() {
        search_button.setOnClickListener {
            val inputText = input_textview.text.toString()

            launch {
                viewModel.getMovieByTitle(inputText)
                bindUI()
            }
        }
    }
}

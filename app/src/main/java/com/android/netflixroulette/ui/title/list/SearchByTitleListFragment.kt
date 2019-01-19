package com.android.netflixroulette.ui.title.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
    private var myAdapter = SearchByTitleEntryAdapter(this@SearchByTitleListFragment)

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

    private fun bindUI() {
        setSearchListener()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchByTitleListFragment.context)
            adapter = myAdapter
            setHasFixedSize(true)
        }

            viewModel.searchByTitleEntries.observe(this@SearchByTitleListFragment, Observer {
            if (it == null) return@Observer

            myAdapter.setList(it.entries)
        })
    }

    override fun onClick(item: SearchByTitleEntry) {
        this.findNavController().navigate(
            SearchByTitleListFragmentDirections.actionSearchByTitleToMovieDetail(item.id)
        )
    }

    private fun setSearchListener() {
        search_button.setOnClickListener {
            val inputText = input_textview.text.toString()

            launch {
                viewModel.getMovieByTitle(inputText)
            }
            it.hideKeyboard()
        }
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}

package com.android.netflixroulette.ui.saved.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.netflixroulette.R
import com.android.netflixroulette.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.saved_movies_list_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SavedMoviesListFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: SavedMoviesListViewModelFactory by instance()
    private lateinit var viewModel: SavedMoviesListViewModel
    private val myAdapter = SavedMoviesListEntryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.saved_movies_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(SavedMoviesListViewModel::class.java)

        bindUI()
    }

    private fun bindUI() {

        saved_list_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@SavedMoviesListFragment.context)
            adapter = myAdapter
            setHasFixedSize(true)
        }

        viewModel.savedMoviesList.observe(this@SavedMoviesListFragment, Observer {
            if (it == null) return@Observer

            myAdapter.setList(it)
        })
    }


}

package com.android.netflixroulette.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.netflixroulette.R
import com.android.netflixroulette.data.database.entity.Movie
import com.android.netflixroulette.ui.base.ScopedFragment
import com.android.netflixroulette.ui.view_model.SharedViewModel
import com.android.netflixroulette.ui.view_model.SharedViewModelFactory
import kotlinx.android.synthetic.main.movie_list_fragment.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SearchByDirectorFragment : ScopedFragment(), KodeinAware, MovieListAdapter.Listener {

    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: SharedViewModelFactory by instance()

    private lateinit var viewModel: SharedViewModel
    private var movieListAdapter = MovieListAdapter(this@SearchByDirectorFragment)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this, viewModelFactory).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        bindUI()

    }

    private fun bindUI() {
        input_textview.visibility = View.VISIBLE
        search_button.visibility = View.VISIBLE

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchByDirectorFragment.context)
            adapter = movieListAdapter
            setHasFixedSize(true)
        }

        viewModel.savedMoviesList.observe(this@SearchByDirectorFragment, Observer {
            if (it == null) return@Observer
            // TODO debug
            Log.d("debug", "savedMoviesList observer called")
            movieListAdapter.setList(it)
        })
    }

    override fun onMovieItemClickListener(item: Movie) {
        Log.d("debug", "click")
        viewModel.setSelectedMovie(item)
        this.findNavController()
            .navigate(
                SearchByDirectorFragmentDirections
                    .actionSearchByDirectorToMovieDetail()
            )
    }
}

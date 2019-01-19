package com.android.netflixroulette.old_ui.ui_v2.list

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
import com.android.netflixroulette.old_ui.base.ScopedFragment
import com.android.netflixroulette.old_ui.ui_v2.view_model.SharedViewModel
import com.android.netflixroulette.old_ui.ui_v2.view_model.SharedViewModelFactory
import kotlinx.android.synthetic.main.movie_list_fragment.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class MovieListFragment : ScopedFragment(), KodeinAware, MovieListAdapter.Listener {

    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: SharedViewModelFactory by instance()

    private lateinit var viewModel: SharedViewModel
    private var movieListAdapter = MovieListAdapter(this@MovieListFragment)

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

        populateDB()
    }

    private fun populateDB() {
        viewModel.saveMovie(Movie(1, "description 1", listOf(), "Aquaman", "/path", "2018", 8.2))
        viewModel.saveMovie(Movie(2, "description 2", listOf(), "Superman", "/path", "2018", 8.2))
        viewModel.saveMovie(Movie(3, "description 3", listOf(), "Batman", "/path", "2018", 8.2))
    }

    private fun bindUI() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MovieListFragment.context)
            adapter = movieListAdapter
            setHasFixedSize(true)
        }

        viewModel.savedMoviesList.observe(this@MovieListFragment, Observer {
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
                MovieListFragmentDirections
                    .actionMovieListToMovieDetail()
            )
    }
}
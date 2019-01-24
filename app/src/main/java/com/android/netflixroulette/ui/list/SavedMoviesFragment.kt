package com.android.netflixroulette.ui.list

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
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


class SavedMoviesFragment : ScopedFragment(), KodeinAware, MovieListAdapter.Listener {

    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: SharedViewModelFactory by instance()

    private lateinit var viewModel: SharedViewModel
    private var movieListAdapter = MovieListAdapter(this@SavedMoviesFragment)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity!!.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recycler_view.layoutManager = GridLayoutManager(context, 1)
        } else {
            recycler_view.layoutManager = GridLayoutManager(context, 2)
        }

        viewModel = activity?.run {
            ViewModelProviders.of(this, viewModelFactory).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        bindUI()

    }

    private fun bindUI() {
        group_loading.visibility = View.VISIBLE
        recycler_view.apply {
            adapter = movieListAdapter
            setHasFixedSize(true)
        }

        viewModel.savedMoviesList.observe(this@SavedMoviesFragment, Observer {
            if (it == null) return@Observer
            group_loading.visibility = View.INVISIBLE
            movieListAdapter.setList(it)
        })
    }

    override fun onMovieItemClickListener(item: Movie) {
        viewModel.setSelectedMovie(item)
        this.findNavController()
            .navigate(
                SavedMoviesFragmentDirections
                    .actionSavedMoviesToSavedDetail()
            )
    }
}

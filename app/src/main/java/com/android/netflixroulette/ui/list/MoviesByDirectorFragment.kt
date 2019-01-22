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
import com.android.netflixroulette.hideKeyboard
import com.android.netflixroulette.ui.base.ScopedFragment
import com.android.netflixroulette.ui.view_model.SharedViewModel
import com.android.netflixroulette.ui.view_model.SharedViewModelFactory
import kotlinx.android.synthetic.main.movie_list_fragment.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
//todo
class MoviesByDirectorFragment : ScopedFragment(), KodeinAware, MovieListAdapter.Listener {

    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: SharedViewModelFactory by instance()

    private lateinit var viewModel: SharedViewModel
    private var movieListAdapter = MovieListAdapter(this@MoviesByDirectorFragment)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = "Movies by director"
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
//        input.visibility = View.VISIBLE

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MoviesByDirectorFragment.context)
            adapter = movieListAdapter
            setHasFixedSize(true)
        }

        viewModel.getMovieByDirectorResponse.observe(this@MoviesByDirectorFragment, Observer {
            if (it == null) return@Observer
            // TODO debug
            Log.d("debug", "searchByTitleResponse observer called")
            movieListAdapter.setFlteredList(it.entries)
        })
//        movieListAdapter.setList(viewModel.getMovieByDirectorResponse)
    }

    override fun onMovieItemClickListener(item: Movie) {
        // todo
        Log.d("debug", "click")
        viewModel.setSelectedMovie(item)
        // todo remove?
        viewModel.setTitle(item.originalTitle)
        view!!.hideKeyboard()
        this.findNavController()
            .navigate(
                MoviesByDirectorFragmentDirections
                    .actionMoviesByDirectorToSearchByDetail()
            )
    }
}
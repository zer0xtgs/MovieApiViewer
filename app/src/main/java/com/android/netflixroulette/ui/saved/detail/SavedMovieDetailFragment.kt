package com.android.netflixroulette.ui.saved.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.android.netflixroulette.R
import com.android.netflixroulette.ui.title.detail.MovieDetailViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SavedMovieDetailFragment : Fragment(), KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory: MovieDetailViewModelFactory by instance()

    private lateinit var viewModel: SavedMovieDetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.saved_movie_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SavedMovieDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

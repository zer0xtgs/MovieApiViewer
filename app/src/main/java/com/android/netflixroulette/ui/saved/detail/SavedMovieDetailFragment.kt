package com.android.netflixroulette.ui.saved.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.netflixroulette.R

class SavedMovieDetailFragment : Fragment() {

    companion object {
        fun newInstance() = SavedMovieDetailFragment()
    }

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

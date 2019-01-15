package com.android.netflixroulette.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.android.netflixroulette.R
import com.android.netflixroulette.ui.base.ScopedFragment

class SavedMoviesFragment : ScopedFragment() {

    companion object {
        fun newInstance() = SavedMoviesFragment()
    }

    private lateinit var viewModel: SavedMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.saved_movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SavedMoviesViewModel::class.java)
        // TODO: Use the ViewModel

//        val apiService = TheMovieDBApiService()
//
//        testTextView
//
//        launch {
//            val movieInfo = apiService.getMovieInfo(297802).await().toString()
//            testTextView.text = movieInfo
//        }



    }

}

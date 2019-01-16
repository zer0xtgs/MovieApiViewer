package com.android.netflixroulette.ui.title.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.android.netflixroulette.R
import com.android.netflixroulette.network.TheMovieDBApiService
import com.android.netflixroulette.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.movie_detail_fragment.*
import kotlinx.coroutines.launch

class MovieDetailFragment : ScopedFragment() {

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
        // TODO: Use the ViewModel

        val id  = arguments!!.getLong("id")

        val apiService = TheMovieDBApiService()

        launch {
            val movieInfo = apiService.getMovieInfo(id).await().toString()
            test_detail_textview.text = movieInfo
        }

    }
}

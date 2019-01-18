package com.android.netflixroulette.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.netflixroulette.R
import com.android.netflixroulette.network.repository.Repository
import com.android.netflixroulette.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.saved_movies_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SavedMoviesFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val repository : Repository by instance()

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

        repository.getSavedMoviesList().observe(this, Observer {
            testTextView.text = it.toString()
        })

    }

}

package com.android.netflixroulette.ui.title.detail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.netflixroulette.R
import com.android.netflixroulette.network.NetworkDataSourceImpl
import com.android.netflixroulette.network.TheMovieDBApiService
import com.android.netflixroulette.network.repository.RepositoryImpl
import com.android.netflixroulette.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.movie_detail_fragment.*
import kotlinx.coroutines.launch

class MovieDetailFragment : ScopedFragment() {

    private val apiService = TheMovieDBApiService()
    private val networkDataSource = NetworkDataSourceImpl(apiService)
    private val repository = RepositoryImpl(networkDataSource)
    private val viewModelFactory = MovieDetailViewModelFactory(repository)
    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.movie_detail_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MovieDetailViewModel::class.java)

        bindUI()

    }

    private fun bindUI() = launch {
        viewModel.getDetailMovieInfo(arguments!!.getLong("id"))

        viewModel.detailMovieResponse.observe(this@MovieDetailFragment, Observer {
            if (it == null) return@Observer
            test_detail_textview.text = it.toString()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.add_button_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.save_button -> Toast.makeText(context, "Saved..", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }
}

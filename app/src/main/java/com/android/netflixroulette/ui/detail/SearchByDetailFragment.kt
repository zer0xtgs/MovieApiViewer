package com.android.netflixroulette.ui.detail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.netflixroulette.GlideApp
import com.android.netflixroulette.R
import com.android.netflixroulette.ui.base.ScopedFragment
import com.android.netflixroulette.ui.view_model.SharedViewModel
import com.android.netflixroulette.ui.view_model.SharedViewModelFactory
import kotlinx.android.synthetic.main.movie_detail_fragment.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SearchByDetailFragment : ScopedFragment(), KodeinAware {

    override val kodein : Kodein by closestKodein()
    private val viewModelFactory : SharedViewModelFactory by instance()

    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.movie_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this, viewModelFactory).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        viewModel.selectedMovie.observe(this, Observer {
            updateActionBarTitle(it.originalTitle)

            GlideApp.with(this)
                .load("https://image.tmdb.org/t/p/w200" + it.posterPath)
                .override(500, 500)
                .centerCrop()
                .into(poster_detail)

            release_detail.text = it.releaseDate
            rating_detail.text = it.voteAverage.toString()
            director_detail.text = "TODO"
            summary_detail.text = it.overview

        })

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.add_button_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.save_button -> {
                Toast.makeText(context, "Saved...", Toast.LENGTH_LONG).show()
                viewModel.saveMovie(viewModel.selectedMovie.value!!)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateActionBarTitle(title: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = title
    }


}

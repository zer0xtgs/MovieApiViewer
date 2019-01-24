package com.android.netflixroulette.ui.list

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.netflixroulette.R
import com.android.netflixroulette.hideKeyboard
import com.android.netflixroulette.network.response.Director
import com.android.netflixroulette.ui.base.ScopedFragment
import com.android.netflixroulette.ui.view_model.SharedViewModel
import com.android.netflixroulette.ui.view_model.SharedViewModelFactory
import kotlinx.android.synthetic.main.movie_list_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SearchByDirectorFragment : ScopedFragment(), KodeinAware, DirectorListAdapter.Listener {

    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: SharedViewModelFactory by instance()

    private lateinit var viewModel: SharedViewModel
    private var directorListAdapter = DirectorListAdapter(this@SearchByDirectorFragment)

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
        initSearchInputListener()
    }

    private fun bindUI() {
        input.hint = "enter director name"
        input.visibility = View.VISIBLE

        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@SearchByDirectorFragment.context)
            adapter = directorListAdapter
            setHasFixedSize(true)
        }

        viewModel.searchDirectorResponse.observe(this@SearchByDirectorFragment, Observer {
            if (it == null) return@Observer
            group_loading.visibility = View.INVISIBLE
            directorListAdapter.setList(it.entries)
        })
    }

    private fun initSearchInputListener() {
        input.setOnEditorActionListener { view: View, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearch(view)
                true
            } else {
                false
            }
        }
        input.setOnKeyListener { view: View, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                doSearch(view)
                true
            } else {
                false
            }
        }
    }

    private fun doSearch(v: View) {
        val inputText = input.text.toString()
        group_loading.visibility = View.VISIBLE
        v.hideKeyboard()
        launch {
            viewModel.getDirector(inputText)
        }
    }

    override fun onMovieItemClickListener(item: Director) {
        launch {
            viewModel.getMovieByDirectorList(item.id)
        }
        view!!.hideKeyboard()
        this.findNavController()
            .navigate(
                SearchByDirectorFragmentDirections
                    .actionSearchByDirectorToMoviesByDirector()
            )
    }
}
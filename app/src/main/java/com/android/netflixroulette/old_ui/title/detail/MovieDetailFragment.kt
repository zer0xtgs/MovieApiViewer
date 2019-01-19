//package com.android.netflixroulette.ui.title.detail
//
//import android.os.Bundle
//import android.view.*
//import android.widget.Toast
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProviders
//import com.android.netflixroulette.R
//import com.android.netflixroulette.ui.base.ScopedFragment
//import kotlinx.android.synthetic.main._movie_detail_fragment.*
//import kotlinx.coroutines.launch
//import org.kodein.di.KodeinAware
//import org.kodein.di.android.x.closestKodein
//import org.kodein.di.generic.instance
//
//class MovieDetailFragment : ScopedFragment(), KodeinAware {
//
//    override val kodein by closestKodein()
//    private val viewModelFactory: MovieDetailViewModelFactory by instance()
//    private lateinit var viewModel: MovieDetailViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        setHasOptionsMenu(true)
//        return inflater.inflate(R.layout._movie_detail_fragment, container, false)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this, viewModelFactory)
//            .get(MovieDetailViewModel::class.java)
//
//        savedInstanceState ?: launch { viewModel.getDetailMovieInfo(arguments!!.getLong("id")) }
//
//        bindUI()
//    }
//
//    private fun bindUI() {
//        viewModel.detailMovieResponse.observe(this@MovieDetailFragment, Observer {
//            if (it == null) return@Observer
//            test_detail_textview.text = it.toString()
//        })
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater?.inflate(R.menu.add_button_menu, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when (item!!.itemId) {
//            R.id.save_button -> {
//                Toast.makeText(context, "Saved...", Toast.LENGTH_LONG).show()
//                viewModel.persistDetailMovie(viewModel.detailMovieResponse.value!!)
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
//}

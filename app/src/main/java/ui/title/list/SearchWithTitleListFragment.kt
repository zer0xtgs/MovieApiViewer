package ui.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.android.netflixroulette.R
import kotlinx.android.synthetic.main.search_with_title_fragment.*
import kotlinx.coroutines.launch
import network.TheMovieDBApiService
import ui.base.ScopedFragment

class SearchWithTitleFragment : ScopedFragment() {

    companion object {
        fun newInstance() = SearchWithTitleFragment()
    }

    private lateinit var viewModel: SearchWithTitleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_with_title_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchWithTitleViewModel::class.java)
        // TODO: Use the ViewModel

        val apiService = TheMovieDBApiService()

        launch {
            val response = apiService.getFilmByTitle("Aquaman").await()
            textView.text = response.entries.first().toString()
        }

//        GlobalScope.async(Main) {
//            textView.text = apiService.getFilmByTitle("Aquaman").await().entries.first().toString()
//        }

//        GlobalScope.launch(Dispatchers.Main) {
//            val movieDeffered  = async(Dispatchers.Default) {
//                apiService.getFilmByTitle("Aquaman")
//            }
//            val infoString = movieDeffered.await()
//            textView.text = infoString.toString()
//        }
    }

}

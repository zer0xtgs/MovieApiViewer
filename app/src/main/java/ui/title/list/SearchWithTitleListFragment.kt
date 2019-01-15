package ui.title.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.netflixroulette.R
import kotlinx.android.synthetic.main.search_with_title_list_fragment.*
import kotlinx.coroutines.launch
import network.TheMovieDBApiService
import network.responce.SearchByTitleEntry
import ui.base.ScopedFragment

class SearchWithTitleListFragment : ScopedFragment() {

    companion object {
        fun newInstance() = SearchWithTitleListFragment()
    }

    private lateinit var viewModel: SearchWithTitleListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_with_title_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchWithTitleListViewModel::class.java)
        // TODO: Use the ViewModel

        val apiService = TheMovieDBApiService()
        var responseListData : List<SearchByTitleEntry>

        launch {
            val response = apiService.getFilmByTitle("Aquaman").await()
            responseListData = response.entries

            recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchWithTitleListFragment.context)
            adapter = SearchByTitleEntryAdapter(responseListData)
            }


        }


    }

}

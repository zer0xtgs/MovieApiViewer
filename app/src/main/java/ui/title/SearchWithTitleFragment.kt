package ui.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.android.netflixroulette.R
import kotlinx.android.synthetic.main.search_with_title_fragment.*
import network.TheMovieDBApiService
import network.responce.SearchByTitleResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchWithTitleFragment : Fragment() {

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

        val theMovieDBApiService = TheMovieDBApiService()
        val searchByTitleResponse = theMovieDBApiService.getFilmByTitle("Aquaman")
            .enqueue(object : Callback<SearchByTitleResponse> {
                override fun onFailure(call: Call<SearchByTitleResponse>, t: Throwable) {
                    textView.text = "failure"
                }

                override fun onResponse(call: Call<SearchByTitleResponse>, response: Response<SearchByTitleResponse>) {
                    textView.text = response.body().toString()
                }

            })
//        textView.text = searchByTitleResponse?.entries.toString()
    }

}

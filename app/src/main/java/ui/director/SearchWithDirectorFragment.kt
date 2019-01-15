package ui.director

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.android.netflixroulette.R
import ui.base.ScopedFragment

class SearchWithDirectorFragment : ScopedFragment() {

    companion object {
        fun newInstance() = SearchWithDirectorFragment()
    }

    private lateinit var viewModel: SearchWithDirectorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_with_director_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchWithDirectorViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

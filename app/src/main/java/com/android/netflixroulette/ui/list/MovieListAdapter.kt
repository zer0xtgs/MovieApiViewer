package com.android.netflixroulette.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.netflixroulette.R
import com.android.netflixroulette.data.database.entity.Movie
import kotlinx.android.synthetic.main.search_with_title_list_item.view.*

class MovieListAdapter(
    private val clickListener: Listener
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    var entryList: List<Movie> = mutableListOf(
// TODO remove
//        Movie(1, "description 1", listOf(), "Aquaman", "/path", "2018", 8.2),
//        Movie(1, "description 2", listOf(), "Superman", "/path", "2018", 4.3),
//        Movie(1, "description 3", listOf(), "Batman", "/path", "2018", 7.3)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.placeholder.text = entryList.elementAt(position).toString()
        holder.itemView.setOnClickListener {
            clickListener.onMovieItemClickListener(entryList.elementAt(position))
        }
    }

    override fun getItemCount() = entryList.size

    fun setList(entryList : List<Movie>){
        this.entryList = entryList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val placeholder: TextView = itemView.placeholder_text_view
    }

    interface Listener {
        fun onMovieItemClickListener(item: Movie)
    }
}
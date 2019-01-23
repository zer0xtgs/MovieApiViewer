package com.android.netflixroulette.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.netflixroulette.GlideApp
import com.android.netflixroulette.R
import com.android.netflixroulette.data.database.entity.Movie

class MovieListAdapter(
    private val clickListener: Listener
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    var entryList: List<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        GlideApp.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w200" + entryList[position].posterPath)
            .override(300, 400)
            .centerCrop()
            .into(holder.poster)
        holder.category.text = entryList[position].genreIds.map { genres[it] }.toString()
        holder.titlee.text = entryList[position].originalTitle
        holder.rating.text = entryList[position].voteAverage.toString()
        holder.release.text = entryList[position].releaseDate.split("-".toRegex()).first()

        holder.itemView.setOnClickListener {
            clickListener.onMovieItemClickListener(entryList[position])
        }
    }

    override fun getItemCount() = entryList.size

    fun setList(entryList: List<Movie>) {
        this.entryList = entryList
        notifyDataSetChanged()
    }

    fun setFilteredList(entryList: List<Movie>) {
        this.entryList = entryList
            .map { it }
            .filter { it.job.equals("director", true) && it.originalTitle != null }
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster = itemView.findViewById<ImageView>(R.id.director_photo)
        val category = itemView.findViewById<TextView>(R.id.director_name)
        val titlee = itemView.findViewById<TextView>(R.id.title)
        val rating = itemView.findViewById<TextView>(R.id.rating)
        val release = itemView.findViewById<TextView>(R.id.release)
    }

    interface Listener {
        fun onMovieItemClickListener(item: Movie)
    }

    val genres = mapOf(
        28 to "Action",
        12 to "Adventure",
        16 to "Animation",
        35 to "Comedy",
        80 to "Crime",
        99 to "Documentary",
        18 to "Drama",
        10751 to "Family",
        14 to "Fantasy",
        36 to "History",
        27 to "Horror",
        9648 to "Mystery",
        10749 to "Romance",
        878 to "Science Fiction",
        10770 to "TV Movie",
        53 to "Thriller",
        10752 to "War",
        37 to "Western")
}

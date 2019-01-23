package com.android.netflixroulette.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.netflixroulette.GlideApp
import com.android.netflixroulette.R
import com.android.netflixroulette.network.response.Director

class DirectorListAdapter(
    private val clickListener: Listener
) : RecyclerView.Adapter<DirectorListAdapter.ViewHolder>() {

    var entryList: List<Director> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.director_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        GlideApp.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w200" + entryList[position].profilePath)
            .placeholder(R.drawable.ic_no_avatar)
            .override(500, 500)
            .centerCrop()
            .into(holder.directorPhoto)

        holder.directorName.text = entryList[position].name

        holder.itemView.setOnClickListener {
            clickListener.onMovieItemClickListener(entryList.elementAt(position))
        }
    }

    override fun getItemCount() = entryList.size

    fun setList(entryList : List<Director>){
        this.entryList = entryList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val directorPhoto = itemView.findViewById<ImageView>(R.id.director_photo)
        val directorName = itemView.findViewById<TextView>(R.id.director_name)
    }

    interface Listener {
        fun onMovieItemClickListener(item: Director)
    }
}
package com.android.netflixroulette.ui.title.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.netflixroulette.R
import com.android.netflixroulette.network.response.SearchByTitleEntry
import kotlinx.android.synthetic.main.search_with_title_list_item.view.*

class SearchByTitleEntryAdapter(
    private val clickListener: Listener
) : RecyclerView.Adapter<SearchByTitleEntryAdapter.ViewHolder>() {

    var entryList: List<SearchByTitleEntry> = mutableListOf()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_with_title_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.placeholder.text = entryList.elementAt(position).toString()
        holder.itemView.setOnClickListener {
            clickListener.onClick(entryList.elementAt(position))
        }
    }

    override fun getItemCount() = entryList.size

    fun setList(entryList : List<SearchByTitleEntry>){
        this.entryList = entryList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val placeholder: TextView = itemView.placeholder_text_view
    }

    interface Listener {
        fun onClick(item: SearchByTitleEntry)
    }
}
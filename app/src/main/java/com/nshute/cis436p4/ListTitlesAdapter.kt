package com.nshute.cis436p4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ListTitlesAdapter(val list: List<TitleHandler>): RecyclerView.Adapter<ListTitlesAdapter.ViewHolder>() {


    @Override
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemViewModel = list[position]
        holder.mediaTitle.text = itemViewModel.title
        holder.mediaYear.text = itemViewModel.year.toString()
        holder.mediaType.text = itemViewModel.type
        holder.mediaGenre.text = itemViewModel.genre
// learn shared preferences
        }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //
        val mediaTitle = itemView.findViewById<TextView>(R.id.titleText)
        val mediaYear = itemView.findViewById<TextView>(R.id.releaseDate)
        val mediaType = itemView.findViewById<TextView>(R.id.typeText)
        val mediaGenre = itemView.findViewById<TextView>(R.id.genreText)

    }


    //

}
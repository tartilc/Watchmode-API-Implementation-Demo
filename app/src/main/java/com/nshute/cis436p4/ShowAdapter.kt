package com.nshute.cis436p4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference
import com.squareup.picasso.Picasso

class ShowAdapter {

    private var Inflater: LayoutInflater? = null
    private var shows_list: MutableList<Show>? = null
    private var context: Context? = null
    private var wrMA: WeakReference<MainActivity>? = null

    fun CatListAdapter(context: Context?, mainActivity: MainActivity) {
        Inflater = LayoutInflater.from(context)
        this.context = context
        shows_list = ArrayList<Show>()
        wrMA = WeakReference(mainActivity)
    }

    fun setCats(cats: MutableList<Show>?) {
        shows_list = cats
    }

    fun getTheCats(): List<Show>? {
        return shows_list
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val itemView = Inflater!!.inflate(R.layout.recyclerview_item, parent, false)
        return ShowViewHolder(itemView)
    }

    fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun getItemViewType(position: Int): Int {
        return position
    }

    /*
    fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        if (shows_list != null) {
            if (shows_list!![position].getDownloaded()) {
                Picasso.with(context).load(theCats!![position].getImageID())
                    .into(holder.imgItemView)
            } else {
                val okHttpHandler =
                    CatImageHandler(theCats!![position].getImageID(), position, this)
                okHttpHandler.execute()
                holder.imgItemView.setImageBitmap(null)
            }
            holder.nameItemView.setText(shows_list!![position].getName())
            holder.descItemView.setText(shows_list!![position].getDescription())
            holder.countryItemView.setText(shows_list!![position].getCountry())
            holder.temperItemView.setText(shows_list!![position].getDescription())
        }
    }

     */

    fun getItemCount(): Int {
        return if (shows_list != null) shows_list!!.size else 0
    }

    fun addCat(catTemp: Show) {
        shows_list!!.add(catTemp)
    }

    class ShowViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        /*
        private val imgItemView: ImageView
        private val nameItemView: TextView
        private val descItemView: TextView
        private val countryItemView: TextView
        private val temperItemView: TextView

        init {
            imgItemView = itemView.findViewById(R.id.image_cat)
            nameItemView = itemView.findViewById(R.id.name_text)
            descItemView = itemView.findViewById(R.id.desc_text)
            countryItemView = itemView.findViewById(R.id.country_text)
            temperItemView = itemView.findViewById(R.id.temper_text)
        }

         */
    }

}
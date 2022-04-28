package com.nshute.cis436p4

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Bottom : Fragment() {

    //var genreBottom = "All"
    //var recyclerView: RecyclerView? = null
    lateinit var recyclerView: RecyclerView
    lateinit var retrofit: Retrofit
    lateinit var listTitles: List<TitleHandler>

    companion object {
        fun newInstance() = Bottom()
    }

    private lateinit var viewModel: BottomViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BottomViewModel::class.java)

        recyclerView = view?.findViewById(R.id.recyclerView)!!
        /*
        val list = ArrayList<TitleHandler>()

        list.add(TitleHandler("Avengers", "Movie", "Animated", 2009))
        list.add(TitleHandler("Lion King", "Movie", "Animated",1994))
        list.add(TitleHandler("Bonnie and Clyde", "Movie", "Animated",1967))
        list.add(TitleHandler("Avatar", "Tv Series", "Animated",2009))
        list.add(TitleHandler("Silicon Valley", "Tv Series", "Animated",2014))
        list.add(TitleHandler("Inception", "Movie", "Animated",2010))
        list.add(TitleHandler("The Lord of the Rings: The Return of the King", "Movie", "Animated",2003))
        list.add(TitleHandler("Braveheart", "Action","Animated", 1995))
        list.add(TitleHandler("Up", "Movie", "Animated",2018))
        list.add(TitleHandler("Rocky", "Movie", "Animated",2018))
        list.add(TitleHandler("Slumdog Millionaire", "Movie", "Animated",2008))
        list.add(TitleHandler("Family Guy", "Tv Series","Animated", 1999))
        list.add(TitleHandler("24", "Tv Series", "Animated",2001))
        list.add(TitleHandler("The Office", "Tv Series","Animated", 2005))
         */

        //val message = intent.getStringExtra("EXTRA")

        retrofit = Retrofit.Builder()//
            .baseUrl("https://api.watchmode.com/v1/")//base url
            .addConverterFactory(GsonConverterFactory.create())//converts with Gson --> needs some converter
            .build()

        val apiService = retrofit.create(APIService::class.java)//interface for APIService

        //genre is sent as message in id # form between activities via intents
        //val apiList = apiService.getTitles(message.toString())
        val apiList = apiService.getTitles("2")

        apiList.enqueue(object : Callback<ListTitlesHandler> {
            //use callBack for multi threaded call
            override fun onFailure(
                call: Call<ListTitlesHandler>,
                t: Throwable
            ) {//is like error checking for failed response within network
                Log.e("ERROR", "FAILED")
            }

            override fun onResponse(
                call: Call<ListTitlesHandler>,
                response: Response<ListTitlesHandler>
            ) {// if this is hit
                listTitles = response.body()?.titles
                    ?: emptyList()//needs null check  --> if respnse body null? id not continue
                //val random = listTitles.random()
                //titleTextView.text = random.title
                //yearTextView.text = random.year.toString()
                //typeTextView.text = random.type

                recyclerView.layoutManager = LinearLayoutManager(context)
                val adapter = ListTitlesAdapter(listTitles)
                recyclerView.adapter = adapter
            }
        })



    }

    fun setGenre(genre:String){
        //type = genre
    }
}
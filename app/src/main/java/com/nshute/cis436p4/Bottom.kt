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
    lateinit var recyclerView: RecyclerView
    lateinit var retrofit: Retrofit
    lateinit var listTitles: List<TitleHandler>
    var message:String? =""

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

        //val message = intent.getStringExtra("EXTRA")

        retrofit = Retrofit.Builder()//
            .baseUrl("https://api.watchmode.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(APIService::class.java)

        //val apiList = apiService.getTitles(message.toString())
        message = arguments?.getString("message")

        val apiList = message?.let { apiService.getTitles(it) }

        if (apiList != null) {
            apiList.enqueue(object : Callback<ListTitlesHandler> {
                override fun onFailure(
                    call: Call<ListTitlesHandler>,
                    t: Throwable
                )

                {
                    Log.e("ERROR", "FAILED")
                }

                override fun onResponse(
                    call: Call<ListTitlesHandler>,
                    response: Response<ListTitlesHandler>
                ) {
                    listTitles = response.body()?.titles
                        ?: emptyList()

                    recyclerView.layoutManager = LinearLayoutManager(context)
                    val adapter = ListTitlesAdapter(listTitles)
                    recyclerView.adapter = adapter
                }
            })
        }
        }
    }

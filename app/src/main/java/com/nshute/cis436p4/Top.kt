package com.nshute.cis436p4

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Top : Fragment() {
    //, AdapterView.OnItemSelectedListener
    //var genre = "All"
    private lateinit var communicator: Communicator
    lateinit var retrofit: Retrofit
    lateinit var genreList: List<GenreHandler>
    lateinit var button: Button
    lateinit var spinner: Spinner

    companion object {
        fun newInstance() = Top()
    }

    private lateinit var viewModel: TopViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        communicator = activity as Communicator

        return inflater.inflate(R.layout.top_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TopViewModel::class.java)

        button = view?.findViewById(R.id.searchButton)!!
        spinner = view?.findViewById(R.id.genreSpinner)!!

        button.setOnClickListener {
            val genre = spinner.selectedItem as GenreHandler

            communicator.passData(genre.id)

            /*
            val intent = Intent(this, NopeSuggestions:: class.java).apply {
                putExtra("EXTRA", genre.id)
            }
            startActivity(intent)
             */
        }

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.watchmode.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(APIService::class.java)
        val apiList = apiService.getGenre()

        apiList.enqueue(object : Callback<List<GenreHandler>> {
            override fun onFailure(call: Call<List<GenreHandler>>, t: Throwable) {
                Log.e("ERROR", "FAILED")
            }

            override fun onResponse(
                call: Call<List<GenreHandler>>,
                response: Response<List<GenreHandler>>
            ) {
                genreList = response.body()!!
                //arrayAdapter()

                val spinnerAdapter: ArrayAdapter<GenreHandler>? = context?.let {
                    ArrayAdapter<GenreHandler>(
                        it, android.R.layout.simple_spinner_item, genreList
                    )
                }

                if (spinnerAdapter != null) {
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                }

                spinner.setAdapter(spinnerAdapter)
            }
        }
        )

        /*
        val adapter: ArrayAdapter<GenreHandler> =
            ArrayAdapter<GenreHandler>(this.requireActivity(), android.R.layout.simple_spinner_item, genreList)


         */

        /*
        val adapter = ArrayAdapter.createFromResource(
            this.requireActivity(),
            genreList,
            android.R.layout.simple_spinner_item
        )

         */

        /*
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        if (spinner != null) {
            spinner.adapter = adapter
            spinner.onItemSelectedListener = this
        }

        if (searchButton.isPressed == true){
            //(activity as MainActivity?)?.displayTitles(genre)
        }

         */
    }

    /*
    fun arrayAdapter(){
        /*
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, genreList)

         */
        val genreList:List<GenreHandler> = genreList.toList()

        val adapter = activity?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_item,
                genreList
            )
        }

        /*
        val adapter = ArrayAdapter<GenreHandler>(this,
            android.R.layout.simple_spinner_item,
            genreList.toList())

         */

        //var adapter = ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, genreList)//look into overriding simple style
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.setAdapter(adapter)
        //spinner.setEnabled(true)
        //spinner.adapter = adapter
    }
    */
}
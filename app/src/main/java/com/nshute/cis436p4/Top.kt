package com.nshute.cis436p4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import android.text.method.ScrollingMovementMethod
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.json.JSONObject
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Top : Fragment(), AdapterView.OnItemSelectedListener {

    companion object {
        fun newInstance() = Top()
    }

    private lateinit var viewModel: TopViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TopViewModel::class.java)

        var searchButtonView: Button? = view?.findViewById(R.id.searchButton)
        //if (searchButtonView != null) {
        //  searchButtonView.setOnClickListener {
        //    GlobalScope.async {
        //  }
        //}

        val spinner = view?.findViewById<Spinner>(R.id.genresSpinner)
        val adapter = ArrayAdapter.createFromResource(
            this.requireActivity(),
            R.array.genres,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        if (spinner != null) {
            spinner.adapter = adapter
        };
        if (spinner != null) {
            spinner.onItemSelectedListener = this
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

}
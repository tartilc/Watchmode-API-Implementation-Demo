package com.nshute.cis436p4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.top_fragment.*
import com.nshute.cis436p4.ShowAdapter as ShowAdapter1

class Top : Fragment(), AdapterView.OnItemSelectedListener {
    var totalShows: List<Show>? = null
    var filter: String? = null
    var catBreeds = arrayOf("Filter breed")

    var genre = "All"
    var spinner = view?.findViewById<Spinner>(R.id.genresSpinner)

    var adapter: ArrayAdapter<String>? = null
    var buttonClear: Button? = null

    var catListAdapter: ShowAdapter1? = null
    var recyclerView: RecyclerView? = null
    var breeds: List<String>? = null

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startUI()
        val okHttpHandler = MainActivity.OkHttpHandler(catListAdapter = ShowAdapter1, this)
        val execute = okHttpHandler.execute()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TopViewModel::class.java)

        var searchButtonView: Button? = view?.findViewById(R.id.searchButton)

        val adapter = ArrayAdapter.createFromResource(
            this.requireActivity(),
            R.array.genres,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        if (spinner != null) {
            spinner!!.adapter = adapter
            spinner!!.onItemSelectedListener = this
        }
        if (searchButton.isPressed == true){
            (activity as MainActivity?)?.displayTitles(genre)
        }
    }

    fun startUI(view: ViewGroup) {
        buttonClear = View.findViewById<Button>(R.id.clearButton)
        filter = resources.getString(R.string.filter)
        totalCats = ArrayList<Cat>()
        MainActivity.breeds = ArrayList<String>()
        spinner = findViewById<Spinner>(R.id.spinner)
        MainActivity.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MainActivity.catBreeds)
        MainActivity.adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.adapter = MainActivity.adapter
        spinner!!.onItemSelectedListener = this
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        (recyclerView.getItemAnimator() as SimpleItemAnimator).supportsChangeAnimations = false
        catListAdapter = CatListAdapter(this, this)
        recyclerView.setAdapter(catListAdapter)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        genre = spinner?.selectedItem.toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        genre = "All"
    }
}

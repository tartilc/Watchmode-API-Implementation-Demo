package com.nshute.cis436p4

import android.R.attr
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


class MainActivity: AppCompatActivity(), Communicator {
    val top: Fragment = Top()
    val bottom: Fragment = Bottom()

    companion object {
        fun newInstance() = MainActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm: FragmentManager = supportFragmentManager
        fm.beginTransaction().replace(R.id.topFragment, top).commit()
        fm.beginTransaction().replace(R.id.bottomFragment, bottom).commit()
    }

    override fun passData(ediTextInput: String) {
        val bundle = Bundle()
        bundle.putString("message", ediTextInput)

        val transaction = this.supportFragmentManager.beginTransaction()

        // Created instance of fragment2
        val bottom = Bottom()

        bottom.arguments = bundle
        transaction.replace(R.id.bottomFragment,bottom)
        transaction.commit()
    }

    /*
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    fun displayTitles(genre: String){
        (bottom as Bottom).setGenre(genre)
    }
    */
}
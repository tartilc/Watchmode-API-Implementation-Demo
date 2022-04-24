package com.nshute.cis436p4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val top: Fragment = Top()
        val bottom: Fragment = Bottom()

        val fm: FragmentManager = supportFragmentManager
        fm.beginTransaction().replace(R.id.topFragment, top).commit()
        fm.beginTransaction().replace(R.id.bottomFragment, bottom).commit()
    }
}
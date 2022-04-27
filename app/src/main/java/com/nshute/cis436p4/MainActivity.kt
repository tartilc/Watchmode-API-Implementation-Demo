package com.nshute.cis436p4

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.lang.ref.WeakReference
import okhttp3.Request.Builder as RequestBuilder

class MainActivity: AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var recyclerView: RecyclerView? = null
    var breeds: List<String>? = null

    var totalCats: List<Cat>? = null
    var filter: String? = null
    var catBreeds = arrayOf("Filter breed")

    var adapter: ArrayAdapter<String>? = null
    var spinner: Spinner? = null
    var buttonClear: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val top: Fragment = Top()
        val bottom: Fragment = Bottom()

        val fm: FragmentManager = supportFragmentManager
        fm.beginTransaction().replace(R.id.topFragment, top).commit()
        fm.beginTransaction().replace(R.id.bottomFragment, bottom).commit()
    }

    class OkHttpHandler internal constructor(catListAdapter: CatListAdapter, context: Context) :
        AsyncTask<String?, Void?, String?>() {
        private val weakRefCatListAdap: WeakReference<CatListAdapter>
        private val context: Context
        override fun onPostExecute(s: String?) {
            super.onPostExecute(s)
            try {
                val jsonArray = JSONArray(s)
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val catTemp = Cat(jsonObject)
                    var breed = ""
                    if (jsonObject.has("name")) {
                        breed = jsonObject["name"].toString()
                    }
                    if (!MainActivity.breeds.contains(breed) && !breed.isEmpty()) MainActivity.breeds.add(
                        breed
                    )
                    totalCats.add(catTemp)
                    weakRefCatListAdap.get().addCat(catTemp)
                    weakRefCatListAdap.get().notifyDataSetChanged()
                }
                MainActivity.breeds.add(0, getResources().getString(R.string.filter))
                MainActivity.catBreeds = MainActivity.breeds.toTypedArray<String>()
                MainActivity.adapter = ArrayAdapter<String>(
                    context,
                    android.R.layout.simple_spinner_item,
                    MainActivity.catBreeds
                )
                MainActivity.adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.setAdapter(MainActivity.adapter)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

        override fun doInBackground(params: Array<String?>): String? {
            val client = OkHttpClient().newBuilder()
                .build()
            val request: Request = Request.Builder()
                .url("https://api.watchmode.com/v1/title/345534/details/?apiKey={YOUR_API_KEY}&append_to_response=sources'")
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", "tLJlKtXy2JBWzDRbaAPbZoQdxx2tbOxU2ZsJi87F")
                .build()
            try {
                val response = client.newCall(request).execute()
                return response.body!!.string()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return null
        }

        init {
            weakRefCatListAdap = WeakReference<CatListAdapter>(catListAdapter)
            this.context = context
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}
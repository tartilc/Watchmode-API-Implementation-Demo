package com.nshute.cis436p4

//import com.example.TvCoPilot.TitlesBO
//import com.example.test_retrofit_setup.CountryBO
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    //    @GET("regions/?apiKey=tLJlKtXy2JBWzDRbaAPbZoQdxx2tbOxU2ZsJi87F")//extended url
    //    fun getCountry (): retrofit2.Call<List<CountryBO>>

    @GET("genres/?apiKey=tLJlKtXy2JBWzDRbaAPbZoQdxx2tbOxU2ZsJi87F")
    fun getGenre (): retrofit2.Call<List<GenreHandler>>

    @GET("list-titles/?apiKey=tLJlKtXy2JBWzDRbaAPbZoQdxx2tbOxU2ZsJi87F")//&genres= 32
    fun getTitles (@Query("genres") genre: String): retrofit2.Call<ListTitlesHandler>
}
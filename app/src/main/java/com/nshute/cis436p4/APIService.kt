package com.nshute.cis436p4

import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    //@GET("regions/?apiKey=tLJlKtXy2JBWzDRbaAPbZoQdxx2tbOxU2ZsJi87F")
    //fun getCountry (): retrofit2.Call<List<CountryHandler>>

    @GET("genres/?apiKey=ieWc4YgWaPP3ZSnFlyRvEPeOREytJdfj0AtCQm1M")
    fun getGenre (): retrofit2.Call<List<GenreHandler>>

    @GET("list-titles/?apiKey=ieWc4YgWaPP3ZSnFlyRvEPeOREytJdfj0AtCQm1M")//&genres= 32
    fun getTitles (@Query("genres") genre: String): retrofit2.Call<ListTitlesHandler>
}
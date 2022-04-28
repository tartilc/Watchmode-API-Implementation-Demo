package com.nshute.cis436p4

import com.google.gson.annotations.SerializedName

data class ListTitlesHandler(

    @SerializedName("titles")
    val titles: List<TitleHandler>,

    @SerializedName("page")
    val page: Int,

    @SerializedName("total_results")
    val results: Int,

    @SerializedName("total_pages")
    val totalPages: Int
    )

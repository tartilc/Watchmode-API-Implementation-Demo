package com.nshute.cis436p4

import com.google.gson.annotations.SerializedName

data class TitleHandler(

    @SerializedName("title")
    val title: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("year")
    val year: Int
)

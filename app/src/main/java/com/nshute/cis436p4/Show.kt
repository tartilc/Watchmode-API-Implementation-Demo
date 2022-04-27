package com.nshute.cis436p4

import org.json.JSONException
import org.json.JSONObject

class Show {
    private var title = " "
    private var plot = " "
    private var type = " "
    private var run_timeMins = " "
    private var release_date = " "
    private var genre_name = " "
    private var rating = " "
    private var poster = " "
    private var org_lang = " "

    fun Show(jsonObject: JSONObject) {
        try {
            if (jsonObject.has("title")) {
                title = jsonObject["title"].toString()
            } else title = ""
            if (jsonObject.has("plot")) {
                plot = jsonObject["plot"].toString()
            } else plot = ""
            if (jsonObject.has("type")) {
                type = jsonObject["type"].toString()
            } else type = ""
            if (jsonObject.has("run_timeMins")) {
                run_timeMins = jsonObject["run_timeMins"].toString()
            } else run_timeMins = ""
            if (jsonObject.has("release_date")) {
                release_date = jsonObject["release_date"].toString()
            } else release_date = ""
            if (jsonObject.has("genre_name")) {
                genre_name = jsonObject["genre_name"].toString()
            } else genre_name = ""
            if (jsonObject.has("rating")) {
                rating = jsonObject["rating"].toString()
            } else rating = ""
            if (jsonObject.has("poster")) {
                poster = jsonObject["poster"].toString()
            } else poster = ""
            if (jsonObject.has("original_language")) {
                org_lang = jsonObject["original_language"].toString()
            } else org_lang = ""
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
    fun setTitle(value:String){ this.title = value }
    fun setPlot(value:String){ this.plot = value }
    fun setType(value:String){ this.type = value }
    fun setRunTimeMins(value:String){ this.run_timeMins = value }
    fun setReleaseDate(value:String){ this.release_date = value }
    fun setGenreName(value:String){ this.genre_name = value }
    fun setRating(value:String){ this.genre_name = value }
    fun setOrgLang(value:String){ this.org_lang = value }

    fun getTitle(value:String):String{ return title}
    fun getPlot(value:String):String{ return plot }
    fun getType(value:String):String{ return type }
    fun getRunTimeMins(value:String):String{ return run_timeMins }
    fun getReleaseDate(value:String):String{ return release_date }
    fun getGenreName(value:String):String{ return genre_name }
    fun getRating(value:String):String{ return rating}
    fun getOrgLang(value:String):String{ return org_lang }
}
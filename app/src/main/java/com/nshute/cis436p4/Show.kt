package com.nshute.cis436p4

import org.json.JSONException
import org.json.JSONObject

class Show {
    private var idNum = " "
        get() = idNum
        set(value) {
            field = value
        }

    private var title = " "
        get() = title
        set(value) {
            field = value
        }

    private var plot = " "
        get() = plot
        set(value) {
            field = value
        }

    private var type = " "
        get() = type
        set(value) {
            field = value
        }

    private var run_timeMins = " "
        get() = run_timeMins
        set(value) {
            field = value
        }

    private var release_date = " "
        get() = release_date
        set(value) {
            field = value
        }

    private var genre_name = " "
        get() = genre_name
        set(value) {
            field = value
        }

    private var rating = " "
        get() = rating
        set(value) {
            field = value
        }

    private var poster = " "
        get() = poster
        set(value) {
            field = value
        }

    fun Show(jsonObject: JSONObject) {
        try {
            if (jsonObject.has("id")) {
                idNum = jsonObject["idNum"].toString()
            } else idNum = ""
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
            if (jsonObject.has("genre_name")) {
                genre_name = jsonObject["genre_name"].toString()
            } else genre_name = ""
            if (jsonObject.has("rating")) {
                rating = jsonObject["rating"].toString()
            } else rating = ""
            if (jsonObject.has("poster")) {
                poster = jsonObject["poster"].toString()
            } else poster = ""
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
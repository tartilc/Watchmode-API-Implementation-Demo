package com.nshute.cis436p4

import org.json.JSONException
import org.json.JSONObject

class Show {
    private var title = " "
    private var plot = " "
    private var type = " "
    private var poster = " "

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
            if (jsonObject.has("poster")) {
                poster = jsonObject["poster"].toString()
            } else poster = ""
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
    fun setTitle(value:String){ this.title = value }
    fun setPlot(value:String){ this.plot = value }
    fun setType(value:String){ this.type = value }

    fun getTitle(value:String):String{ return title}
    fun getPlot(value:String):String{ return plot }
    fun getType(value:String):String{ return type }
}
package com.example.myapplication.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonUtils {

    private var gson : Gson? = null
    val gsonParser: Gson? get() {
        if (null == gson) {
            val builder = GsonBuilder()
            gson = builder.create()
        }
        return gson
    }

}
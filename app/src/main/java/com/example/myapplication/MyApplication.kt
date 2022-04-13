package com.example.myapplication

import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        System.out.println("hiii")
    }
}
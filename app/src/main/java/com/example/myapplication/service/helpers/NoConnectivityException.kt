package com.example.myapplication.service.helpers

import java.io.IOException

class NoConnectivityException : IOException() {

    override val message: String?
        get() = "No Internet Connection"

    override fun getLocalizedMessage(): String? {
        return "No Internet Connection"
    }

}
package com.seniah.news.helper

import com.seniah.news.NewsApp
import javax.inject.Inject

class Logger @Inject constructor() {
    fun log(message: String) {
        android.util.Log.e(NewsApp::class.simpleName, message)
    }
}

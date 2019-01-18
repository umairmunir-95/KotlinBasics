package com.tallymarks.kotlinbasics

import android.app.Application

class App : Application() {
    companion object {
        const val BASE_URL = "https://api.github.com/"
        const val GET_REPOSITORIES="users/umairmunir-95/repos"
    }
}

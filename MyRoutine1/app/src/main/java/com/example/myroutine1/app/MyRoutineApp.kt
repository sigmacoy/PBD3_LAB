package com.example.myroutine1.app

import android.app.Application
import android.content.Context

class MyRoutineApp : Application() {
    fun saveUser(username: String) {
        val prefs = getSharedPreferences("myroutine_user", Context.MODE_PRIVATE)
        prefs.edit()
            .putString("username", username)
            .putBoolean("logged_in", true)
            .apply()
    }
}
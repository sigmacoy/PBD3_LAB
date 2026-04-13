package com.example.myroutine1

import android.app.Application
import com.example.myroutine1.data.MidnightResetReceiver

class MyRoutineApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MidnightResetReceiver.scheduleMidnightReset(this)
    }
}
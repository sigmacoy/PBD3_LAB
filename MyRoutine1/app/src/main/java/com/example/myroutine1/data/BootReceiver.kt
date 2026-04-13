package com.example.myroutine1.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            MidnightResetReceiver.scheduleMidnightReset(context)
            // re-schedule all task reminders here (if needed pud)

        }
    }
}
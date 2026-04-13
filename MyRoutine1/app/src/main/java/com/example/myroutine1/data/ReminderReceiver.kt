package com.example.myroutine1.data

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.myroutine1.R
import com.example.myroutine1.data.repositories.TaskRepository
import java.util.Calendar

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Thread {
            val pending = TaskRepository(context).getPendingCount()
            if (pending > 0) {
                showNotification(context, pending)
            }
        }.start()
    }

    private fun showNotification(context: Context, pendingCount: Int) {
        val channelId = "myroutine_reminders"
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, "Daily Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply { description = "MyRoutine habit reminders" }
            manager.createNotificationChannel(channel)
        }
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setContentTitle("Don't break your streak! ■■")
            .setContentText("You still have $pendingCount task(s) to complete today.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()
        manager.notify(1001, notification)
    }

    companion object {
        fun scheduleReminder(context: Context, hourOfDay: Int, minute: Int, requestCode: Int) {
            val target = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hourOfDay)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                if (before(Calendar.getInstance())) add(Calendar.DAY_OF_YEAR, 1)
            }
            val intent = Intent(context, ReminderReceiver::class.java)
            val pi = PendingIntent.getBroadcast(
                context, requestCode, intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, target.timeInMillis, pi)
        }
    }
}

// scheduleReminder
// Schedule a one-shot reminder at the given HH:mm each day.
// Call this whenever a task with a reminderTime is added/updated.
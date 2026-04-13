package com.example.myroutine1.data.models

data class Task(
    val id: String = System.currentTimeMillis().toString(),
    val title: String,
    val reminderTime: String? = null, // "HH:mm", e.g. "20:00"
    val isCompleted: Boolean = false,
    val streakCount: Int = 0,
    val lastCompletedDate: String? = null
)
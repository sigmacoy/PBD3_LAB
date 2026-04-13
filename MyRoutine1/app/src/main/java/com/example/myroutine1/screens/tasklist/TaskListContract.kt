package com.example.myroutine1.screens.tasklist

import com.example.myroutine1.data.models.Task

interface TaskListContract {

    interface View {
        fun showTasks(tasks: List<Task>)
        fun showEmptyState()
        fun showCurrentDay(dayName: String, dateStr: String)
        fun updateStreak(days: Int)
        fun navigateToAddTask()
        fun showMessage(msg: String)
    }

    interface Presenter {
        fun loadTasks()
        fun onTaskChecked(taskId: String, isChecked: Boolean)
        fun onTaskDeleted(taskId: String)
        fun onAddTaskClicked()
        fun onDestroy()
    }
}
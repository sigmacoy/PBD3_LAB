package com.example.myroutine1.screens.tasklist

import android.os.Handler
import android.os.Looper
import com.example.myroutine1.data.models.Task
import com.example.myroutine1.data.repositories.TaskRepository
import java.text.SimpleDateFormat
import java.util.*

class TaskListPresenter(
    private var view: TaskListContract.View?,
    private val repo: TaskRepository
) : TaskListContract.Presenter {
    private val mainHandler = Handler(Looper.getMainLooper())

    override fun loadTasks() {
        // Show current day & date
        val cal = Calendar.getInstance()
        val day = SimpleDateFormat("EEEE", Locale.getDefault()).format(cal.time)
        val date = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault()).format(cal.time)
        view?.showCurrentDay(day, date)
        Thread {
            val tasks = repo.getAllTasks()
            mainHandler.post {
                if (tasks.isEmpty()) view?.showEmptyState()
                else {
                    view?.showTasks(tasks)
                    view?.updateStreak(tasks.firstOrNull()?.streakCount ?: 0)
                }
            }
        }.start()
    }

    override fun onTaskChecked(taskId: String, isChecked: Boolean) {
        Thread {
            val task = repo.getTaskById(taskId) ?: return@Thread
            val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            val updated = task.copy(
                isCompleted = isChecked,
                streakCount = if (isChecked) task.streakCount + 1 else task.streakCount,
                lastCompletedDate = if (isChecked) today else task.lastCompletedDate
            )
            repo.updateTask(updated)
            val all = repo.getAllTasks()
            mainHandler.post { view?.showTasks(all) }
        }.start()
    }

    override fun onTaskDeleted(taskId: String) {
        Thread {
            repo.deleteTask(taskId)
            val all = repo.getAllTasks()
            mainHandler.post {
                if (all.isEmpty()) view?.showEmptyState()
                else view?.showTasks(all)
            }
        }.start()
    }

    override fun onAddTaskClicked() {
        view?.navigateToAddTask()
    }

    override fun onDestroy() {
        view = null
    }
}
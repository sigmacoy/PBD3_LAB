package com.example.myroutine1.screens.addtask
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.example.myroutine1.data.MidnightResetReceiver
import com.example.myroutine1.data.ReminderReceiver
import com.example.myroutine1.data.models.Task
import com.example.myroutine1.data.repositories.TaskRepository

class AddTaskPresenter(
    private var view: AddTaskContract.View?,
    private val repo: TaskRepository,
    private val context: Context // needed to schedule AlarmManager
) : AddTaskContract.Presenter {
    private val mainHandler = Handler(Looper.getMainLooper())
    override fun onSaveClicked(title: String, reminderTime: String?) {
        view?.clearErrors()
        val trimmed = title.trim()
        when {
            trimmed.isBlank() -> { view?.showTitleError("Task name cannot be empty."); return }
            trimmed.length > 60 -> { view?.showTitleError("Max 60 characters."); return }
        }

        // Validate HH:mm format
        val validTime = reminderTime?.takeIf {
            it.matches(Regex("""^([01]\d|2[0-3]):([0-5]\d)$"""))
        }

        view?.setSubmitEnabled(false)

        Thread {
            try {
                val task = Task(title = trimmed, reminderTime = validTime)
                repo.addTask(task)
                // Schedule per-task alarm if reminder set
                validTime?.let { time ->
                    val parts = time.split(":")
                    ReminderReceiver.scheduleReminder(
                        context,
                        parts[0].toInt(),
                        parts[1].toInt(),
                        task.id.hashCode()
                    )
                }
                mainHandler.post {
                    view?.showSuccess(trimmed)
                    mainHandler.postDelayed({ view?.navigateBack() }, 700)
                }
            } catch (e: Exception) {
                mainHandler.post { view?.showTitleError("Failed to save. Try again.") }
            } finally {
                mainHandler.post { view?.setSubmitEnabled(true) }
            }
        }.start()
    }

    override fun onDestroy() {
        view = null
    }
}
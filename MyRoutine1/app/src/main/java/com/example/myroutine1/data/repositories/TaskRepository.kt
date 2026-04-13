package com.example.myroutine1.data.repositories

import android.content.Context
import android.content.SharedPreferences
import com.example.myroutine1.data.models.Task
import org.json.JSONArray
import org.json.JSONObject

class TaskRepository(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // READ
    fun getAllTasks(): List<Task> {
        val json = prefs.getString(KEY_TASKS, "[]") ?: "[]"
        val result = mutableListOf<Task>()
        val arr = JSONArray(json)
        for (i in 0 until arr.length()) {
            result.add(fromJson(arr.getJSONObject(i)))
        }
        return result
    }

    fun getTaskById(id: String): Task? = getAllTasks().firstOrNull {
        it.id == id
    }

    // WRITE
    fun addTask(task: Task) {
        val tasks = getAllTasks().toMutableList()
        tasks.add(task)
        saveTasks(tasks)
    }

    fun updateTask(updated: Task) {
        val tasks = getAllTasks().map { if (it.id == updated.id) updated else it }
        saveTasks(tasks)
    }

    fun deleteTask(id: String) {
        val tasks = getAllTasks().filter { it.id != id }
        saveTasks(tasks)
    }

    fun resetAllTasks() {
        val tasks = getAllTasks().map {
            it.copy(isCompleted = false)
        }
        saveTasks(tasks)
    }

    fun getPendingCount(): Int = getAllTasks().count {
        !it.isCompleted
    }

    // PRIVATE
    private fun saveTasks(tasks: List<Task>) {
        val arr = JSONArray()
        tasks.forEach { arr.put(toJson(it)) }
        prefs.edit().putString(KEY_TASKS, arr.toString()).apply()
    }

    private fun toJson(t: Task): JSONObject = JSONObject().apply {
        put("id", t.id)
        put("title", t.title)
        put("reminderTime", t.reminderTime ?: JSONObject.NULL)
        put("isCompleted", t.isCompleted)
        put("streakCount", t.streakCount)
        put("lastCompletedDate",t.lastCompletedDate ?: JSONObject.NULL)
    }

    private fun fromJson(o: JSONObject) = Task(
        id = o.getString("id"),
        title = o.getString("title"),
        reminderTime = if (o.isNull("reminderTime")) null else o.getString("reminderTime"),
        isCompleted = o.getBoolean("isCompleted"),
        streakCount = o.getInt("streakCount"),
        lastCompletedDate = if (o.isNull("lastCompletedDate")) null else o.getString("lastCompletedDate")
    )

    companion object {
        const val PREFS_NAME = "myroutine_tasks"
        const val KEY_TASKS = "tasks_json"
    }
}

/**
 * Stores tasks as a JSON array string in SharedPreferences.
 * No Room, no external JSON library — uses Android's built-in org.json.
 */
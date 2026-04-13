package com.example.myroutine1.screens.tasklist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myroutine1.R
import com.example.myroutine1.data.models.Task
import com.example.myroutine1.data.repositories.TaskRepository
import com.example.myroutine1.screens.addtask.AddTaskActivity

class TaskListActivity : AppCompatActivity(), TaskListContract.View {
    private lateinit var presenter: TaskListPresenter
    private lateinit var listviewTasks: ListView
    private lateinit var textviewEmpty: TextView
    private lateinit var textviewStreak: TextView
    private lateinit var textviewDay: TextView
    private lateinit var textviewDate: TextView
    private lateinit var buttonAdd: Button
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)
        textviewDay = findViewById(R.id.textviewDayName)
        textviewDate = findViewById(R.id.textviewDate)
        textviewStreak= findViewById(R.id.textviewStreak)
        textviewEmpty = findViewById(R.id.textviewEmpty)
        listviewTasks = findViewById(R.id.listviewTasks)
        buttonAdd = findViewById(R.id.buttonAdd)
        adapter = TaskAdapter(this, mutableListOf(),
            onChecked = { id, checked -> presenter.onTaskChecked(id, checked) },
            onDelete = { id -> presenter.onTaskDeleted(id) }
        )
        listviewTasks.adapter = adapter
        presenter = TaskListPresenter(this, TaskRepository(this))
        buttonAdd.setOnClickListener { presenter.onAddTaskClicked() }
        presenter.loadTasks()
    }

    override fun showTasks(tasks: List<Task>) {
        textviewEmpty.visibility = View.GONE
        listviewTasks.visibility = View.VISIBLE
        adapter.replaceTasks(tasks)
    }

    override fun showEmptyState() {
        listviewTasks.visibility = View.GONE
        textviewEmpty.visibility = View.VISIBLE
    }

    override fun showCurrentDay(dayName: String, dateStr: String) {
        textviewDay.text = dayName
        textviewDate.text = dateStr
    }

    override fun updateStreak(days: Int) {
        textviewStreak.text = "$days day streak"
    }

    override fun navigateToAddTask() {
        startActivityForResult(Intent(this, AddTaskActivity::class.java), 100)
    }

    override fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    // Refresh list when returning from AddTaskActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) presenter.loadTasks()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
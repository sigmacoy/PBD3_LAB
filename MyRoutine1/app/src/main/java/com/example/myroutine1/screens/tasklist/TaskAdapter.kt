package com.example.myroutine1.screens.tasklist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import com.example.myroutine1.R
import com.example.myroutine1.data.models.Task

class TaskAdapter(
    context: Context,
    private val tasks: MutableList<Task>,
    private val onChecked: (String, Boolean) -> Unit,
    private val onDelete: (String) -> Unit
) : ArrayAdapter<Task>(context, 0, tasks) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_task, parent, false)
        val task = tasks[position]
        val checkboxTask = view.findViewById<CheckBox>(R.id.checkboxTask)
        val textviewReminder = view.findViewById<TextView>(R.id.textviewReminder)
        val buttonDelete = view.findViewById<ImageButton>(R.id.buttonDelete)

        // Prevent stale listener firing during bind
        checkboxTask.setOnCheckedChangeListener(null)
        checkboxTask.text = task.title
        checkboxTask.isChecked = task.isCompleted
        textviewReminder.text = task.reminderTime?.let { "■ $it" } ?: ""
        textviewReminder.visibility = if (task.reminderTime != null) View.VISIBLE else View.GONE
        checkboxTask.setOnCheckedChangeListener { _, checked -> onChecked(task.id, checked) }
        buttonDelete.setOnClickListener { onDelete(task.id) }

        return view
    }

    fun replaceTasks(newTasks: List<Task>) {
        tasks.clear()
        tasks.addAll(newTasks)
        notifyDataSetChanged()
    }
}
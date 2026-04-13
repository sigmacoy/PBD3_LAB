package com.example.myroutine1.screens.addtask

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myroutine1.R
import com.example.myroutine1.data.repositories.TaskRepository

class AddTaskActivity : AppCompatActivity(), AddTaskContract.View {
    private lateinit var presenter: AddTaskPresenter
    private lateinit var edittextTitle: EditText
    private lateinit var edittextReminder: EditText
    private lateinit var textviewError: TextView
    private lateinit var textviewSuccess: TextView
    private lateinit var buttonSave: Button
    private lateinit var buttonCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        supportActionBar?.apply { title = "Add New Habit"; setDisplayHomeAsUpEnabled(true) }
        edittextTitle = findViewById(R.id.edittextTaskTitle)
        edittextReminder = findViewById(R.id.edittextReminderTime)
        textviewError = findViewById(R.id.textviewError)
        textviewSuccess = findViewById(R.id.textviewSuccess)
        buttonSave = findViewById(R.id.buttonSave)
        buttonCancel = findViewById(R.id.buttonCancel)
        presenter = AddTaskPresenter(this, TaskRepository(this), applicationContext)
        buttonSave.setOnClickListener {
            val time = edittextReminder.text.toString().trim().ifBlank { null }
            presenter.onSaveClicked(edittextTitle.text.toString(), time)
        }
        buttonCancel.setOnClickListener {
            finish()
        }
    }

    override fun showTitleError(msg: String) {
        textviewError.text = msg
        textviewError.visibility = View.VISIBLE
    }

    override fun showSuccess(name: String) {
        textviewSuccess.text = "$name added!"
        textviewSuccess.visibility = View.VISIBLE
    }

    override fun clearErrors() {
        textviewError.visibility = View.GONE
        textviewSuccess.visibility = View.GONE
    }

    override fun navigateBack() {
        setResult(RESULT_OK);
        finish()
    }

    override fun setSubmitEnabled(e: Boolean){
        buttonSave.isEnabled = e
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onDestroy() {
        super.onDestroy();
        presenter.onDestroy()
    }
}
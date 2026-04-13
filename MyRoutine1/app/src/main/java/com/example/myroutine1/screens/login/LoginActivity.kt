package com.example.myroutine1.screens.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myroutine1.R
import com.example.myroutine1.screens.tasklist.TaskListActivity

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private lateinit var presenter: LoginPresenter
    private lateinit var edittextUsername: EditText
    private lateinit var textviewError: TextView
    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences(LoginPresenter.PREFS_NAME, MODE_PRIVATE)
        presenter = LoginPresenter(this, prefs)
        setContentView(R.layout.activity_login)
        edittextUsername = findViewById(R.id.edittextUsername)
        textviewError = findViewById(R.id.textviewError)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonLogin.setOnClickListener {
            presenter.onLoginClicked(edittextUsername.text.toString())
        }
    }

    override fun showUsernameError(message: String) {
        textviewError.text = message
        textviewError.visibility = View.VISIBLE
    }

    override fun clearErrors() {
        textviewError.text = ""
        textviewError.visibility = View.GONE
    }

    override fun navigateToTaskList() {
        startActivity(Intent(this, TaskListActivity::class.java))
        finish()
    }

    override fun setLoginButtonEnabled(enabled: Boolean) {
        buttonLogin.isEnabled = enabled
    }

    override fun onDestroy() {
        super.onDestroy(); presenter.onDestroy()
    }
}
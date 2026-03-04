package com.example.finalproject_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var edittextUsernameLogin: EditText
    private lateinit var edittextPasswordLogin: EditText
    private lateinit var buttonLogin: Button
    private lateinit var textviewRegisterLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edittextUsernameLogin = findViewById(R.id.edittextUsernameLogin)
        edittextPasswordLogin = findViewById(R.id.edittextPasswordLogin)
        buttonLogin = findViewById(R.id.buttonLogin)
        textviewRegisterLogin = findViewById(R.id.textviewRegisterLogin)

        buttonLogin.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        textviewRegisterLogin.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
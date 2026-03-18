package com.example.finalproject_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    private var edittextUsernameRegister: EditText? = null
    private var edittextPasswordRegister: EditText? = null
    private var edittextReEnterPasswordRegister: EditText? = null
    private var buttonSubmit: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        edittextUsernameRegister = findViewById(R.id.edittextUsernameRegister)
        edittextPasswordRegister = findViewById(R.id.edittextPasswordRegister)
        edittextReEnterPasswordRegister = findViewById(R.id.edittextReEnterPasswordRegister)
        buttonSubmit = findViewById(R.id.buttonSubmit)

        buttonSubmit?.setOnClickListener {
            val intent = Intent(
                this@RegisterActivity,
                LoginActivity::class.java
            )
            startActivity(intent)
        }
    }
}
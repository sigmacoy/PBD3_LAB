package com.example.finalproject_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    private var buttonBackToDashboard: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        buttonBackToDashboard = findViewById(R.id.buttonBackToDashboard)

        buttonBackToDashboard?.setOnClickListener {
            val intent = Intent(
                this@ProfileActivity,
                DashboardActivity::class.java
            )
            startActivity(intent)
        }
    }
}
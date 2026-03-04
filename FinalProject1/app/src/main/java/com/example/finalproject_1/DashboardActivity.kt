package com.example.finalproject_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {
    private var buttonGoToProfileScreen: Button? = null
    private var buttonDashboardLogout: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        buttonGoToProfileScreen = findViewById(R.id.buttonGoToProfileScreen)
        buttonDashboardLogout = findViewById(R.id.buttonDashboardLogout)

        buttonGoToProfileScreen?.setOnClickListener {
            val intent = Intent(
                this@DashboardActivity,
                ProfileActivity::class.java
            )
            startActivity(intent)
        }

        buttonDashboardLogout?.setOnClickListener {
            val intent = Intent(
                this@DashboardActivity,
                LoginActivity::class.java
            )
            startActivity(intent)
        }
    }
}
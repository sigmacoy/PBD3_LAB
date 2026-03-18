package com.example.finalproject_1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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

class DashboardActivity2 : Activity(){
    override fun onCreate(bundle : Bundle ?){
        super.onCreate(bundle);
        setContentView(R.layout.activity_dashboard);

        val textviewWelcome = findViewById<TextView>(R.id.textviewWelcome);
        intent?.getStringExtra("username")?.let {
            textviewWelcome.text = "Welcome $it."; // this is the setter
        }
        textviewWelcome.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java);
            // attach data to intent
            startActivity(intent);
        }
    }
}
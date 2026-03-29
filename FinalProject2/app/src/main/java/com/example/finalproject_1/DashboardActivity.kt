package com.example.finalproject_1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : Activity(){
    override fun onCreate(bundle : Bundle ?){
        super.onCreate(bundle);
        setContentView(R.layout.activity_dashboard);

        val textviewWelcome = findViewById<TextView>(R.id.textviewWelcome);
        val storedPref = getSharedPreferences("user", MODE_PRIVATE);

        storedPref.getString("usernameRegistered", "NULL")?.let {
            textviewWelcome.text = "Welcome $it."; // this is the setter
        }

        textviewWelcome.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java);
            startActivity(intent);
        }
    }
}


package com.example.finalproject_1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView

class ProfileActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // textviewProfileMessage
        // textviewProfileEmail

        val textviewProfileMessage = findViewById<TextView>(R.id.textviewProfileMessage);

        val storedPref = getSharedPreferences("user", MODE_PRIVATE);
        storedPref.getString("usernameRegistered", "NULL")?.let {
            textviewProfileMessage.text = it; // this is the setter
        }

        val textviewProfileEmail = findViewById<TextView>(R.id.textviewProfileEmail);

        storedPref.getString("email", "NULL")?.let {
            textviewProfileEmail.text = "$it"; // this is the setter
        }

    }
}
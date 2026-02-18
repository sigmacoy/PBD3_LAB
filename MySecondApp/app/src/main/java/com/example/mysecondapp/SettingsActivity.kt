package com.example.mysecondapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class SettingsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_settings)

        val buttonProfile = findViewById<Button>(R.id.button_profile)
        buttonProfile.setOnClickListener {
            Toast.makeText(
                this,
                "Button is clicked. I love you Jelian",
                Toast.LENGTH_LONG
            ).show()

            val intent = Intent(
                this,
                ProfileActivity::class.java
            )
            startActivity(intent)
        }
    }
}
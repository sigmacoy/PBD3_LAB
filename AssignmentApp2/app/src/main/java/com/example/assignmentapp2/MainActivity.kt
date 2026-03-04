package com.example.assignmentapp2

// Write sa yellow pad

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_routine_layout)

        val btnAddRoutine: Button = findViewById(R.id.btnAddRoutine)
        val chkVitamins: CheckBox = findViewById(R.id.chkVitamins)
        val chkLeetcode: CheckBox = findViewById(R.id.chkLeetcode)
        val chkMilk: CheckBox = findViewById(R.id.chkMilk)

        chkVitamins.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Take Vitamins is checked!", Toast.LENGTH_SHORT).show()
            }
        }
        chkLeetcode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Solve Leetcode Problems is checked!", Toast.LENGTH_SHORT).show()
            }
        }
        chkMilk.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Drink Milk is checked!", Toast.LENGTH_SHORT).show()
            }
        }
        btnAddRoutine.setOnClickListener {
            Toast.makeText(this, "Add routine button is pressed!", Toast.LENGTH_SHORT).show()
        }
    }
}

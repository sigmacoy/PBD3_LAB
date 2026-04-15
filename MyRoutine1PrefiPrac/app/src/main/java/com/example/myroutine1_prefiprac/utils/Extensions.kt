package com.example.myroutine1_prefiprac.utils

import android.app.Activity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

fun Activity.toast(msg: String)
        = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

fun Activity.getEditTextValue(id: Int): String
        = findViewById<EditText>(id).text.toString()

fun Activity.getButtonView(id: Int): Button
        = findViewById(id)
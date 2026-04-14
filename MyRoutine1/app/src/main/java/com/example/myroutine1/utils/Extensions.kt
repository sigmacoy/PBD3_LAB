package com.example.myroutine1.utils

import android.app.Activity
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myroutine1.app.MyRoutineApp

fun Activity.app() = application as MyRoutineApp

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.getEditTextValue(id: Int): String {
    return findViewById<EditText>(id).text.toString()
}

fun Activity.getButtonView(id: Int): Button {
    return findViewById(id)
}

fun <T> Activity.start(it: Class<T>) {
    startActivity(Intent(this, it))
    finish()
}
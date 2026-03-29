package com.example.finalproject_1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RegisterActivity : Activity() {
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_register)

        val edittextUsername = findViewById<EditText>(R.id.edittextUsername);
        val edittextPassword = findViewById<EditText>(R.id.edittextPassword);
        val edittextReEnterPassword = findViewById<EditText>(R.id.edittextReEnterPassword);
        val edittextEmail = findViewById<EditText>(R.id.edittextEmail);
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit);

        buttonSubmit?.setOnClickListener {
            val email = edittextEmail.text.toString();
            val username = edittextUsername.text.toString();
            val password = edittextPassword.text.toString();
            val password2 = edittextReEnterPassword.text.toString();

            if(!username.isNullOrEmpty() && !email.isNullOrEmpty() && password == password2){
                val intent = Intent(this, LoginActivity::class.java);

                val sharedPref = getSharedPreferences("user", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.clear().apply() // ----------------- Clears the previous “keys” (the before app run)
                editor.putString("email", email)
                editor.putString("usernameRegistered", username)
                editor.putString("passwordRegistered", password)
                editor.apply()

                startActivity(intent);
            }
            if(username.isNullOrEmpty() || password.isNullOrEmpty() || password2.isNullOrEmpty() || email.isNullOrEmpty()){
                Toast.makeText(this, "Try Again!", Toast.LENGTH_SHORT).show()
            } else if(password != password2){
                Toast.makeText(this, "Password not the same!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

package com.example.finalproject_1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : Activity(){
    override fun onCreate(bundle: Bundle ?){
        super.onCreate(bundle);
        setContentView(R.layout.activity_login);
        val edittextUsername = findViewById<EditText>(R.id.edittextUsername);
        val edittextPassword = findViewById<EditText>(R.id.edittextPassword);
        val textviewRegister = findViewById<TextView>(R.id.textviewRegister);
        val buttonLogin = findViewById<Button>(R.id.buttonLogin);

        textviewRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent);
        }

        buttonLogin.setOnClickListener {
            val username = edittextUsername.text.toString(); // text.toString() is the getter
            val password = edittextPassword.text.toString();

            val storedPref = getSharedPreferences("user", MODE_PRIVATE);
            val usernameRegistered = storedPref.getString("usernameRegistered", "NULL");
            val passwordRegistered = storedPref.getString("passwordRegistered", "NULL");

            if(!username.isNullOrEmpty() &&
                !password.isNullOrEmpty() &&
                username == usernameRegistered &&
                password == passwordRegistered){
                val intent = Intent(this, DashboardActivity::class.java);
                startActivity(intent);
                // below is not reachable...
            }
            if(username.isNullOrEmpty() || password.isNullOrEmpty() ){
                Toast.makeText(this, "Try Again!", Toast.LENGTH_SHORT).show()
            } else if(username != usernameRegistered ||
                password != passwordRegistered){
                Toast.makeText(this, "Incorrect username or password!\nTry again",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}
/*

 */
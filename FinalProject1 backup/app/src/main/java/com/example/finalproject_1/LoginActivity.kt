package com.example.finalproject_1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
//class LoginActivity : Activity() {
//
//    private lateinit var edittextUsernameLogin: EditText
//    private lateinit var edittextPasswordLogin: EditText
//    private lateinit var buttonLogin: Button
//    private lateinit var textviewRegisterLogin: TextView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//
//        edittextUsernameLogin = findViewById(R.id.edittextUsernameLogin)
//        edittextPasswordLogin = findViewById(R.id.edittextPasswordLogin)
//        buttonLogin = findViewById(R.id.buttonLogin)
//        textviewRegisterLogin = findViewById(R.id.textviewRegisterLogin)
//
//        buttonLogin.setOnClickListener {
//            val intent = Intent(this, DashboardActivity::class.java)
//            startActivity(intent)
//        }
//
//        textviewRegisterLogin.setOnClickListener {
//            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)
//        }
//    }
//}

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
            if(!username.isNullOrEmpty() && !password.isNullOrEmpty()){
                val intent = Intent(this, DashboardActivity::class.java);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                startActivity(intent);
            }
            // putExtra(key, value) -> is the key-value pair
        }
    }
}
/*

 */
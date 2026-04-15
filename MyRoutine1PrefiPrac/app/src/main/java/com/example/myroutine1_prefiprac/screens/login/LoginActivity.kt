package com.example.myroutine1_prefiprac.screens.login

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.myroutine1_prefiprac.R
import com.example.myroutine1_prefiprac.utils.getButtonView
import com.example.myroutine1_prefiprac.utils.getEditTextValue
import com.example.myroutine1_prefiprac.utils.toast

class LoginActivity : Activity(), LoginContract.View {
    private lateinit var presenter: LoginPresenter
    private lateinit var textviewError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        textviewError = findViewById(R.id.textviewError)
        presenter = LoginPresenter(this, LoginModel())

        getButtonView(R.id.buttonLogin).setOnClickListener {
            val username = getEditTextValue(R.id.edittextUsername)
            val password = getEditTextValue(R.id.edittextPassword)
            presenter.onLoginClicked(username, password)
        }

        getButtonView(R.id.buttonRegister).setOnClickListener {
            presenter.onRegisterClicked()
        }
    }

    override fun showInputError(msg: String) {
        textviewError.text = msg
        textviewError.visibility = View.VISIBLE
    }

    override fun clearErrors() {
        textviewError.text = ""
        textviewError.visibility = View.GONE
    }

    override fun showSuccessToast() = toast("Login successful!")
    override fun showErrorToast(msg: String) = toast("Login failed: $msg")
    override fun showRegisterToast() = toast("To the Register screen!")
}
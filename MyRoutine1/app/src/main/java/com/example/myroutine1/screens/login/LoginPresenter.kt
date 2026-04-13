package com.example.myroutine1.screens.login

import android.content.SharedPreferences

class LoginPresenter(
    private var view: LoginContract.View?,
    private val prefs: SharedPreferences
) : LoginContract.Presenter {

    override fun onLoginClicked(username: String) {
        view?.clearErrors()
        val trimmed = username.trim()
        when {
            trimmed.isBlank() -> view?.showUsernameError("Please enter your name to continue.")
            trimmed.length < 2 -> view?.showUsernameError("Name must be at least 2 characters.")
            else -> {
                prefs.edit()
                    .putString(KEY_USERNAME, trimmed)
                    .putBoolean(KEY_LOGGED_IN, true)
                    .apply()
                view?.navigateToTaskList()
            }
        }
    }

    override fun isAlreadyLoggedIn(): Boolean =
        prefs.getBoolean(KEY_LOGGED_IN, false)

    override fun onDestroy() {
        view = null
    }

    companion object {
        const val PREFS_NAME = "myroutine_user"
        const val KEY_USERNAME = "username"
        const val KEY_LOGGED_IN = "logged_in"
    }
}
package com.example.myroutine1.screens.login

interface LoginContract {
    interface View {
        fun showUsernameError(message: String)
        fun clearErrors()
        fun navigateToTaskList()
        fun setLoginButtonEnabled(enabled: Boolean)
    }
    interface Presenter {
        fun onLoginClicked(username: String)
        fun isAlreadyLoggedIn(): Boolean
        fun onDestroy()
    }
}
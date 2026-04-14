package com.example.myroutine1_prefiprac.screens.login

interface LoginContract {
    interface View {
        fun clearErrors()
        fun showInputError(msg: String)
        fun showSuccessToast()
        fun showErrorToast(msg: String)
    }
    interface Presenter {
        fun onLoginClicked(u: String, p: String)
    }
}
package com.example.myroutine1_prefiprac.screens.login

class LoginPresenter(
    private val view: LoginContract.View,
    private val model: LoginModel,
) : LoginContract.Presenter {

    override fun onLoginClicked(u: String, p: String) {
        view.clearErrors()
        when (val result = model.validate(u, p)) {
            is LoginModel.ValidationResult.Success -> {
                view.showSuccessToast()
            }
            is LoginModel.ValidationResult.Error -> {
                view.showErrorToast(result.msg)
                view.showInputError(result.msg)
            }
        }
    }
}
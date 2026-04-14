package com.example.myroutine1_prefiprac.screens.login

class LoginModel {
    fun validate(u: String, p: String): ValidationResult {
        val trimmedUser = u.trim()
        return when {
            trimmedUser.isBlank() -> ValidationResult.Error("Name cannot be empty.")
            p.isBlank() -> ValidationResult.Error("Password cannot be empty.")
            else -> ValidationResult.Success(trimmedUser)
        }
    }

    sealed class ValidationResult {
        data class Success(val name: String) : ValidationResult()
        data class Error(val msg: String) : ValidationResult()
    }
}
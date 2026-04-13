package com.example.myroutine1.screens.addtask

interface AddTaskContract {
    interface View {
        fun showTitleError(message: String)
        fun showSuccess(name: String)
        fun clearErrors()
        fun navigateBack()
        fun setSubmitEnabled(enabled: Boolean)
    }
    interface Presenter {
        fun onSaveClicked(title: String, reminderTime: String?)
        fun onDestroy()
    }
}
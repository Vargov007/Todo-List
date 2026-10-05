package com.example.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewmodelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(TaskViewmodel::class.java)){
            return TaskViewmodel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
package com.example.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.Todo_Items
import com.example.todolist.database.TaskDatabase
import com.example.todolist.repository.TaskRepo
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewmodel(application: Application): AndroidViewModel(application) {

    private val dao = TaskDatabase.getDatabase(application).taskdao()
    private val repo = TaskRepo(dao)

    val alltasks : SharedFlow<List<Todo_Items>> = repo.getItems()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun addtask(task : Todo_Items){
        viewModelScope.launch {
            repo.insert(task)
        }
    }

    fun deletetask(task: Todo_Items){
        viewModelScope.launch {
            repo.delete(task)
        }
    }
}
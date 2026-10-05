package com.example.todolist.repository

import com.example.todolist.dao_repo.TaskDao
import com.example.todolist.data.Todo_Items
import kotlinx.coroutines.flow.Flow

class TaskRepo(private val dao : TaskDao) {

    fun getItems(): Flow<List<Todo_Items>>{
        return dao.allgetItem()

    }

    suspend fun insert (task: Todo_Items) = dao.Insert(task)

    suspend fun update(task: Todo_Items) = dao.Update(task)

    suspend fun delete(task: Todo_Items) = dao.Delete(task)
}
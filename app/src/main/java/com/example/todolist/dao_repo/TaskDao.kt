package com.example.todolist.dao_repo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todolist.data.Todo_Items
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insert(task: Todo_Items)

    @Delete
    suspend fun Delete(task: Todo_Items)

    @Update
    suspend fun Update(task: Todo_Items)

    @Query("SELECT * FROM todoItems Order BY id DESC ")
    fun allgetItem(): Flow<List<Todo_Items>>
}
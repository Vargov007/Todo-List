package com.example.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("todoItems")
data class Todo_Items(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val description: String,
    val date: String,
)

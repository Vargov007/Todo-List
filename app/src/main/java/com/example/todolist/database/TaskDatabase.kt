package com.example.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase // 1. Add this import
import com.example.todolist.dao_repo.TaskDao
import com.example.todolist.data.Todo_Items

@Database([Todo_Items::class], version = 2)
abstract class TaskDatabase : RoomDatabase() { // 2. Add : RoomDatabase()

    abstract fun taskdao(): TaskDao

    companion object {

        @Volatile
        private var Instance: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            return Instance ?: synchronized(this) {

                Room.databaseBuilder(
                    context.applicationContext,
                    klass = TaskDatabase::class.java,
                    name = "task_database"
                )
                    .fallbackToDestructiveMigration()
                    // .addMigrations() -> Removed because it needs migration parameters
                    .build()
                    .also { Instance = it }

            }
        }
    }
}
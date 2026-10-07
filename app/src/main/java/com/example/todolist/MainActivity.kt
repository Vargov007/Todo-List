package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolist.ui.screens.TodoListScreen
import com.example.todolist.ui.theme.TodoListTheme
import com.example.todolist.viewmodel.TaskViewmodel
import com.example.todolist.viewmodel.ViewmodelFactory

class MainActivity : ComponentActivity() {

    val viewmodel : TaskViewmodel by viewModels(){
        ViewmodelFactory(application)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoListTheme {
                TodoListScreen(viewmodel)



            }
        }
    }
}

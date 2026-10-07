package com.example.todolist.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.data.Todo_Items
import com.example.todolist.viewmodel.TaskViewmodel

@Composable
fun TodoListScreen(viewmodel: TaskViewmodel) {
    val task by viewmodel.alltasks.collectAsState(initial = emptyList())

    var taskEdit by remember { mutableStateOf<Todo_Items?>(null) }
    var showEditorDialog by remember { mutableStateOf(false) }          //for edit task

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    taskEdit = null
                    showEditorDialog = true
                },
                shape = RoundedCornerShape(20.dp),
                containerColor = Color.Black,
                contentColor = Color.White,
                elevation = FloatingActionButtonDefaults.elevation(10.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add Icon",
                )

                Text(
                    text = "New Task",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 16.sp,
                )
            }
        },
    ) { innerPadding ->

        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            Text(
                text = "My Tasks",
                modifier = Modifier.padding(18.dp),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
            )

            Text(
                text = "${task.filter { !it.isDone }.size} remaining today",
                modifier = Modifier.padding(start = 25.dp),
            )

            if (task.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "No Tasks",
                        color = Color.Gray,
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(bottom = 80.dp),
                ) {
                    items(
                        items = task,
                        key = { it.id },
                    ) { task ->

                        TodoListItems(
                            items = task,
                            onEditTask = { taskEdit = task; showEditorDialog = true },
                            onDeleteTask = { viewmodel.deletetask(task) },
                            onChecked = {chacked ->
                                viewmodel.updatetask(task.copy(isDone = chacked))
                            },
                        )
                    }
                }
            }


            if (showEditorDialog){

                TaskListEditor(
                    task = taskEdit,
                    onSave = { newTitle ->
                        if (taskEdit == null){
                            viewmodel.addtask(Todo_Items(title = newTitle, isDone = false))
                        }else{
                           taskEdit?.let {viewmodel.updatetask(it.copy(title = newTitle)) }

                        }
                        taskEdit = null
                        showEditorDialog = false
                    },
                    onCancel = {
                        taskEdit = null
                        showEditorDialog = false
                    }
                )
            }
        }
    }
}

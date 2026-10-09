package com.example.todolist.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material3.SmallFloatingActionButton
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
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.data.Todo_Items
import com.example.todolist.viewmodel.TaskViewmodel

@Composable
fun TodoListScreen(viewmodel: TaskViewmodel) {
    val task by viewmodel.alltasks.collectAsState(initial = emptyList())

    var taskEdit by remember { mutableStateOf<Todo_Items?>(null) }
    var showEditorDialog by remember { mutableStateOf(false) }



    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    taskEdit = null
                    showEditorDialog = true
                },
                interactionSource = remember { MutableInteractionSource() },
                shape = RoundedCornerShape(20.dp),
                containerColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                contentColor = if (isSystemInDarkTheme())Color.Black else Color.White,
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
                modifier = Modifier.padding(top = 22.dp, start = 18.dp),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSystemInDarkTheme()) Color.White else Color.DarkGray,
            )

            Text(
                text = "${task.filter { !it.isDone }.size} remaining today",
                modifier = Modifier.padding(end = 25.dp)
                    .align(Alignment.End),
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
                    ) { taskItem ->

                        TodoListItems(
                            items = taskItem,
                            onEditTask = { taskEdit = taskItem; showEditorDialog = true },
                            onDeleteTask = { viewmodel.deletetask(taskItem) },
                            onChecked = { chacked ->
                                viewmodel.updatetask(taskItem.copy(isDone = chacked))
                            },
                        )
                    }
                }
            }

            if (showEditorDialog) {
                TaskListEditor(
                    task = taskEdit,
                    onSave = { newTitle ->
                        if (taskEdit == null) {
                            viewmodel.addtask(Todo_Items(title = newTitle, isDone = false))
                        } else {
                            taskEdit?.let { viewmodel.updatetask(it.copy(title = newTitle)) }
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

//@Composable
//fun popUp(){
//
//    var isExpanded by remember { mutableStateOf(false) }
//
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        AnimatedVisibility(
//            visible = isExpanded,
//            enter = fadeIn(animationSpec = tween(durationMillis = 200)) +
//                    scaleIn(
//                        animationSpec = tween(durationMillis = 200),
//                        transformOrigin = TransformOrigin(0.5f, 1f)
//                    ),
//            exit = fadeOut(animationSpec = tween(durationMillis = 150)) +
//                    scaleOut(
//                        animationSpec = tween(durationMillis = 150),
//                        transformOrigin = TransformOrigin(0.5f, 1f)
//                    )
//        ) {
//            SmallFloatingActionButton(
//                onClick = {}
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "add Icon",
//                )
//
//                Text(
//                    text = "New Task",
//                    modifier = Modifier.padding(8.dp),
//                    fontSize = 16.sp,
//                )
//            }
//        }
//    }
//}
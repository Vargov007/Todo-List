package com.example.todolist.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.todolist.ui.theme.lightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListEditor(
    task: Todo_Items?,
    onSave: (String) -> Unit,
    onCancel: () -> Unit,
) {
    var textName by remember { mutableStateOf(task?.title ?: "") }

    ModalBottomSheet(
        onDismissRequest = onCancel,
        containerColor = Color.White,
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .navigationBarsPadding(),
        ) {
            Text(
                text = if (task == null)"Create New Task"  else "Edit This Task",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(
                modifier = Modifier.height(20.dp),
            )

            OutlinedTextField(
                value = textName,
                onValueChange = { textName = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "What's needs to be done",
                    )
                },
                colors =
                    OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.DarkGray,
                        unfocusedBorderColor = Color.Gray,
                    ),
                shape = RoundedCornerShape(20.dp),
            )

            Spacer(
                modifier = Modifier.height(10.dp),
            )

            IconButton(
                onClick = { onSave(textName.trim()) },
                modifier =
                    Modifier
                        .background(lightBlue, CircleShape)
                        .align(Alignment.End),
                enabled = textName.isNotBlank()
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    "check",
                    tint = Color.White,
                )
            }
        }
    }
}

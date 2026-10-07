package com.example.todolist.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.data.Todo_Items

@Composable
fun TodoListItems(
    items : Todo_Items,
    onEditTask: () -> Unit,
    onDeleteTask: () -> Unit,
    onChecked: (Boolean) -> Unit

) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (items.isDone) Color.LightGray else Color.White
        ),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(3.dp, Color.Gray)
    ) {

        //is Checked button
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {onChecked(!items.isDone)}
            ) {
                Icon(
                    imageVector = if(items.isDone) Icons.Filled.Circle
                        else Icons.Default.RadioButtonUnchecked,
                    contentDescription = null,
                    tint = if (items.isDone) Color.Green else Color.Gray
                )
            }

            Spacer(
                modifier = Modifier.width(7.dp)
            )

            Text(
                text = items.title,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f),
                color = if (items.isDone) Color.Gray else Color.DarkGray,
                textDecoration = if (items.isDone) TextDecoration.LineThrough else null

            )

            Row {

                IconButton(
                    onClick = onEditTask,
                    enabled = !items.isDone
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = Color.DarkGray
                    )
                }


                    IconButton(
                        onClick = onDeleteTask
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            tint = Color.DarkGray

                        )
                    }



            }

        }
    }

}
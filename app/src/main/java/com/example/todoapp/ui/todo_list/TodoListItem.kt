package com.example.todoapp.ui.todo_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todoapp.data.local.entities.Todo
import com.example.todoapp.ui.theme.DarkPrimary
import com.example.todoapp.ui.theme.LightPrimary

@Composable
fun TodoListItem(
    todo: Todo,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clickable { onClick(todo.id) },
        elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                containerColor = LightPrimary

                )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = todo.title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = if (todo.isDone) "✔️ Completed" else "⌛ Pending",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

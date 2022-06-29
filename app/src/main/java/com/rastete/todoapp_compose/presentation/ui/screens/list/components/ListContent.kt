package com.rastete.todoapp_compose.presentation.ui.screens.list.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rastete.todoapp_compose.domain.TodoTask

@Composable
fun ListContent(
    modifier: Modifier = Modifier,
    todoTaskList: List<TodoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(
            items = todoTaskList,
            key = { todoTask ->
                todoTask.id ?: 0
            }
        ) { todoTask ->
            TodoItem(
                todoTask = todoTask,
                navigateToTaskScreen = navigateToTaskScreen
            )
        }
    }
}
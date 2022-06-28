package com.rastete.todoapp_compose.presentation.ui.screens.task.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rastete.todoapp_compose.domain.Priority
import com.rastete.todoapp_compose.domain.TodoTask
import com.rastete.todoapp_compose.presentation.util.Action

@Composable
fun TaskAppBar(
    title: String,
    navigateToListScreen: (Action) -> Unit
) {
    if (title.isEmpty()) {
        NewTaskAppBAr(
            navigateToListScreen = navigateToListScreen
        )
    } else {
        ExistingTaskAppBAr(
            title = title,
            navigateToListScreen = navigateToListScreen
        )
    }
}

@Composable
@Preview
fun NewTaskAppBarPreview() {
    NewTaskAppBAr(navigateToListScreen = {})
}

@Composable
@Preview
fun ExistingTaskAppBarPreview() {
    ExistingTaskAppBAr(
        title = "Todo Task title",
        navigateToListScreen = {})
}
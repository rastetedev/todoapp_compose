package com.rastete.todoapp_compose.presentation.ui.screens.task.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rastete.todoapp_compose.presentation.util.Action
import com.rastete.todoapp_compose.presentation.util.Constants.DEFAULT_NO_TASK_ID

@Composable
fun TaskAppBar(
    title: String,
    id: Int,
    navigateToListScreen: (Action) -> Unit
) {
    if (id == DEFAULT_NO_TASK_ID) {
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
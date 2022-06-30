package com.rastete.todoapp_compose.presentation.ui.screens.task.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rastete.todoapp_compose.presentation.ui.screens.Screen
import com.rastete.todoapp_compose.presentation.util.Action
import com.rastete.todoapp_compose.presentation.viewmodel.TaskScreenState

@Composable
fun TaskAppBar(
    taskScreenState: TaskScreenState,
    navigateToListScreen: (Action) -> Unit
) {
    if (taskScreenState.id == Screen.DEFAULT_TASK_ID_ARGUMENT_VALUE) {
        NewTaskAppBAr(
            navigateToListScreen = navigateToListScreen
        )
    } else {
        ExistingTaskAppBAr(
            title = taskScreenState.title,
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
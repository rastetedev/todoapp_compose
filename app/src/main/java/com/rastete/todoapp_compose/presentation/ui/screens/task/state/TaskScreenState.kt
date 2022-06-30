package com.rastete.todoapp_compose.presentation.ui.screens.task.state

import com.rastete.todoapp_compose.domain.Priority
import com.rastete.todoapp_compose.presentation.ui.screens.Screen

data class TaskScreenState(
    val id: Int = Screen.DEFAULT_TASK_ID_ARGUMENT_VALUE,
    val title: String = "",
    val description: String = "",
    val priority: Priority = Priority.LOW
)
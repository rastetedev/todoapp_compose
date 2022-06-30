package com.rastete.todoapp_compose.presentation.ui.screens

sealed class Screen(val route: String) {
    object TaskListScreen : Screen(TASKS_SCREEN)
    object TaskScreen : Screen(TASK_SCREEN)


    companion object {
        private const val TASKS_SCREEN = "tasks_screen"
        private const val TASK_SCREEN = "task_screen"

        const val ACTION_ARGUMENT_KEY = "action"
        const val TASK_ID_ARGUMENT_KEY = "taskId"

        const val DEFAULT_ACTION_ARGUMENT_VALUE = "NO_ACTION"
        const val DEFAULT_TASK_ID_ARGUMENT_VALUE = -1
    }
}
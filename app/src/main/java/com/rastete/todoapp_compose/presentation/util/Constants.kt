package com.rastete.todoapp_compose.presentation.util

object Constants {

    const val DEFAULT_NO_TASK_ID = -1
    const val MAX_TITLE_LENGTH = 20

    object Navigation {
        const val LIST_ARGUMENT_KEY = "action"
        const val TASK_ARGUMENT_KEY = "taskId"

        const val LIST_SCREEN = "list/{$LIST_ARGUMENT_KEY}"
        const val TASK_SCREEN = "task/{$TASK_ARGUMENT_KEY}"
    }


}
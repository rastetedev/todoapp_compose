package com.rastete.todoapp_compose.presentation.navigation.destinations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rastete.todoapp_compose.presentation.ui.screens.Screen
import com.rastete.todoapp_compose.presentation.ui.screens.task.TaskScreen

fun NavGraphBuilder.taskComposable(
    navController: NavController
) {
    composable(
        route = Screen.TaskScreen.route + "?taskId={taskId}",
        arguments = listOf(
            navArgument(Screen.TASK_ID_ARGUMENT_KEY) {
                type = NavType.IntType
                defaultValue = Screen.DEFAULT_TASK_ID_ARGUMENT_VALUE
            }
        )
    ) {
        TaskScreen(navController = navController)
    }
}
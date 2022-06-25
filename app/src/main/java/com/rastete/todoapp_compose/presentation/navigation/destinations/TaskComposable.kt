package com.rastete.todoapp_compose.presentation.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rastete.todoapp_compose.presentation.util.Action
import com.rastete.todoapp_compose.presentation.util.Constants.Navigation.TASK_ARGUMENT_KEY
import com.rastete.todoapp_compose.presentation.util.Constants.Navigation.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen : (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(
            navArgument(TASK_ARGUMENT_KEY) {
                type = NavType.IntType
            }
        )
    ) {

    }
}
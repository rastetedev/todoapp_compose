package com.rastete.todoapp_compose.presentation.navigation.destinations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rastete.todoapp_compose.presentation.ui.screens.Screen
import com.rastete.todoapp_compose.presentation.ui.screens.Screen.Companion.ACTION_ARGUMENT_KEY
import com.rastete.todoapp_compose.presentation.ui.screens.Screen.Companion.DEFAULT_ACTION_ARGUMENT_VALUE
import com.rastete.todoapp_compose.presentation.ui.screens.list.ListScreen

fun NavGraphBuilder.listComposable(
    navController: NavController
) {
    composable(
        route = Screen.TaskListScreen.route + "/{action}",
        arguments = listOf(
            navArgument(name = ACTION_ARGUMENT_KEY) {
                type = NavType.StringType
                defaultValue = DEFAULT_ACTION_ARGUMENT_VALUE
            }
        )
    ) { navBackStackEntry ->
        ListScreen(navController = navController)
    }

}
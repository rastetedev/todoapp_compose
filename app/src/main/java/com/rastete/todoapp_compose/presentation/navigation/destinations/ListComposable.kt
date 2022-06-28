package com.rastete.todoapp_compose.presentation.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rastete.todoapp_compose.presentation.ui.screens.list.ListScreen
import com.rastete.todoapp_compose.presentation.util.Constants.Navigation.LIST_ARGUMENT_KEY
import com.rastete.todoapp_compose.presentation.util.Constants.Navigation.LIST_SCREEN
import com.rastete.todoapp_compose.presentation.viewmodel.ListViewModel

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    listViewModel: ListViewModel
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(
            navArgument(LIST_ARGUMENT_KEY) {
                type = NavType.StringType
            }
        )
    ) {
        ListScreen(
            navigateToTaskScreen = navigateToTaskScreen,
            listViewModel = listViewModel
        )
    }
}
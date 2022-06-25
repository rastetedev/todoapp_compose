package com.rastete.todoapp_compose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.rastete.todoapp_compose.presentation.navigation.destinations.listComposable
import com.rastete.todoapp_compose.presentation.navigation.destinations.taskComposable
import com.rastete.todoapp_compose.presentation.util.Constants.Navigation.LIST_SCREEN
import com.rastete.todoapp_compose.presentation.viewmodel.TodoSharedViewModel

@Composable
fun SetupNavigation(
    navHostController: NavHostController,
    sharedViewModel: TodoSharedViewModel
) {

    val screen = remember(navHostController) {
        ScreensSetup(navHostController)
    }

    NavHost(
        navController = navHostController,
        startDestination = LIST_SCREEN
    ) {
        listComposable(
            navigateToTaskScreen = screen.task,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            navigateToListScreen = screen.list
        )
    }
}

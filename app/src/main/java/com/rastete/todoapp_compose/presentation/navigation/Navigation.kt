package com.rastete.todoapp_compose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.rastete.todoapp_compose.presentation.navigation.destinations.listComposable
import com.rastete.todoapp_compose.presentation.navigation.destinations.splashComposable
import com.rastete.todoapp_compose.presentation.navigation.destinations.taskComposable
import com.rastete.todoapp_compose.presentation.ui.screens.Screen

@Composable
fun SetupNavigation(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.SplashScreen.route
    ) {
        splashComposable(
            navController = navHostController
        )
        listComposable(
            navController = navHostController
        )
        taskComposable(
            navController = navHostController
        )
    }
}

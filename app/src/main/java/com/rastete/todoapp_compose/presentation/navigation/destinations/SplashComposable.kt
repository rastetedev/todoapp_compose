package com.rastete.todoapp_compose.presentation.navigation.destinations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rastete.todoapp_compose.presentation.ui.screens.Screen
import com.rastete.todoapp_compose.presentation.ui.screens.splash.SplashScreen


fun NavGraphBuilder.splashComposable(
    navController: NavController
) {
    composable(
        route = Screen.SplashScreen.route
    ) {
        SplashScreen(navController = navController)
    }

}
package com.rastete.todoapp_compose.presentation.navigation

import androidx.navigation.NavController
import com.rastete.todoapp_compose.presentation.util.Action
import com.rastete.todoapp_compose.presentation.util.Constants.Navigation.LIST_SCREEN

class ScreensSetup(navController: NavController) {

    val list: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN) {
                inclusive = true
            }
        }
    }

    val task: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId")
    }
}

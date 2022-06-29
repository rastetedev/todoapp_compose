package com.rastete.todoapp_compose.presentation.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rastete.todoapp_compose.presentation.ui.screens.task.TaskScreen
import com.rastete.todoapp_compose.presentation.util.Action
import com.rastete.todoapp_compose.presentation.util.Constants.Navigation.TASK_ARGUMENT_KEY
import com.rastete.todoapp_compose.presentation.util.Constants.Navigation.TASK_SCREEN
import com.rastete.todoapp_compose.presentation.viewmodel.TaskViewModel

fun NavGraphBuilder.taskComposable(
    taskViewModel: TaskViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(
            navArgument(TASK_ARGUMENT_KEY) {
                type = NavType.IntType
            }
        )
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments?.getInt(TASK_ARGUMENT_KEY)

        LaunchedEffect(key1 = taskId) {
            taskViewModel.getSelectedTask(taskId)
        }

        TaskScreen(
            taskViewModel = taskViewModel,
            navigateToListScreen = { action ->
                when (action) {
                    Action.ADD -> {
                        taskViewModel.addTask()
                        navigateToListScreen(action)

                    }
                    Action.UPDATE -> {
                        taskViewModel.updateTask()
                        navigateToListScreen(action)

                    }
                    Action.DELETE -> {
                        taskViewModel.deleteTask()
                        navigateToListScreen(action)

                    }
                    else -> {
                        navigateToListScreen(action)
                    }
                }
            }
        )
    }
}
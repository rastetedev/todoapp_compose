package com.rastete.todoapp_compose.presentation.ui.screens.task

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rastete.todoapp_compose.R
import com.rastete.todoapp_compose.presentation.ui.DisplayAlertDialog
import com.rastete.todoapp_compose.presentation.ui.screens.Screen
import com.rastete.todoapp_compose.presentation.ui.screens.task.components.TaskAppBar
import com.rastete.todoapp_compose.presentation.ui.screens.task.components.TaskContent
import com.rastete.todoapp_compose.presentation.ui.screens.task.state.TaskScreenState
import com.rastete.todoapp_compose.presentation.util.Action
import com.rastete.todoapp_compose.presentation.viewmodel.TaskViewModel

@Composable
fun TaskScreen(
    taskViewModel: TaskViewModel = hiltViewModel(),
    navController: NavController,
) {
    val taskScreenState: TaskScreenState by taskViewModel.taskScreenState

    var isOpenDialog by remember {
        mutableStateOf(false)
    }

    if (isOpenDialog) {
        DisplayAlertDialog(
            title = "Remove ${taskScreenState.title}",
            message = "Are you sure you want to remove ${taskScreenState.title}?",
            openDialog = isOpenDialog,
            closeDialog = {
                isOpenDialog = false
            },
            onYesClick = {
                taskViewModel.deleteTask()
                navController.navigate(Screen.TaskListScreen.route + "/DELETE") {
                    popUpTo(Screen.TaskListScreen.route + "/{action}") {
                        inclusive = true
                    }
                }
            }
        )
    }

    val context = LocalContext.current

    BackHandler(onBack = {
        navController.navigateUp()
    })


    Scaffold(
        topBar = {
            TaskAppBar(taskScreenState) { action ->

                if (action == Action.NO_ACTION) {
                    navController.navigateUp()
                } else {
                    if (taskViewModel.areValidFields()) {
                        when (action) {
                            Action.ADD -> {
                                taskViewModel.addTask()
                                navController.navigate(Screen.TaskListScreen.route + "/$action") {
                                    popUpTo(Screen.TaskListScreen.route + "/{action}") {
                                        inclusive = true
                                    }
                                }
                            }
                            Action.DELETE -> isOpenDialog = true
                            Action.UPDATE -> {
                                taskViewModel.updateTask()
                                navController.navigate(Screen.TaskListScreen.route + "/$action") {
                                    popUpTo(Screen.TaskListScreen.route + "/{action}") {
                                        inclusive = true
                                    }
                                }
                            }
                            else -> Unit
                        }

                    } else {
                        showToast(context)
                    }
                }
            }
        },
        content = {
            TaskContent(
                modifier = Modifier.padding(it),
                title = taskScreenState.title,
                onTitleChange = { title ->
                    taskViewModel.changeTitle(title)
                },
                description = taskScreenState.description,
                onDescriptionChange = { description ->
                    taskViewModel.changeDescription(description)
                },
                priority = taskScreenState.priority,
                onPrioritySelected = { priority ->
                    taskViewModel.changePriority(priority)
                }
            )
        }
    )
}

fun showToast(context: Context) {
    Toast.makeText(context, context.getString(R.string.empty_fields), Toast.LENGTH_SHORT).show()
}

/*
@Composable
fun BackHandler(
    backDispatcher: OnBackPressedDispatcher? = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher,
    onBackPressed: () -> Unit
) {
    val currentOnBackPressed by rememberUpdatedState(newValue = onBackPressed)
    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }
        }
    }

    DisposableEffect(key1 = backDispatcher) {
        backDispatcher?.addCallback(backCallback)
        onDispose {
            backCallback.remove()
        }
    }
}*/

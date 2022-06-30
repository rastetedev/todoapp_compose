package com.rastete.todoapp_compose.presentation.ui.screens.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rastete.todoapp_compose.R
import com.rastete.todoapp_compose.domain.TodoTask
import com.rastete.todoapp_compose.presentation.ui.screens.Screen
import com.rastete.todoapp_compose.presentation.ui.screens.Screen.Companion.DEFAULT_TASK_ID_ARGUMENT_VALUE
import com.rastete.todoapp_compose.presentation.ui.screens.list.components.*
import com.rastete.todoapp_compose.presentation.util.Action
import com.rastete.todoapp_compose.presentation.util.RequestState
import com.rastete.todoapp_compose.presentation.util.SearchAppBarState
import com.rastete.todoapp_compose.presentation.util.toAction
import com.rastete.todoapp_compose.presentation.viewmodel.ListViewModel
import com.rastete.todoapp_compose.presentation.viewmodel.SharedViewModel
import kotlinx.coroutines.launch

@Composable
fun ListScreen(
    navController: NavController,
    listViewModel: ListViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = true) {
        listViewModel.getAllTasks()
    }

    val tasksRequestState by listViewModel.tasks.collectAsState()
    val searchAppBarState: SearchAppBarState by listViewModel.searchAppBarState
    val searchTextState: String by listViewModel.searchTextState
    val scaffoldState = rememberScaffoldState()

    /*if (action.toAction() == Action.DELETE) {
        *//*SnackBar(
            scaffoldState = scaffoldState,
            todoTask = sharedViewModel.getTodoTask(),
            message = stringResource(id = R.string.restore_task),
            actionLabel = stringResource(id = R.string.ok)
        ) {
            sharedViewModel.restoreTask()
        }*//*
    }*/

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ListAppBar(
                searchAppBarState = searchAppBarState,
                onSearchAppBarStateChange = {
                    listViewModel.changeSearchAppBarState(it)
                },
                onSearchTextChange = {
                    listViewModel.changeSearchText(it)
                },

                searchTextState = searchTextState,
                onSearchClick = {
                    listViewModel.search()
                },
                onDeleteAllClick = {
                    listViewModel.deleteAllTasks()
                }
            )
        },
        content = { paddingValues ->
            if (tasksRequestState is RequestState.Success) {
                if ((tasksRequestState as RequestState.Success<List<TodoTask>>).data.isEmpty()) {
                    ListEmptyContent()
                } else {
                    ListContent(
                        modifier = Modifier.padding(paddingValues),
                        todoTaskList = (tasksRequestState as RequestState.Success<List<TodoTask>>).data,
                        navigateToTaskScreen = { taskId ->
                            navController.navigate(Screen.TaskScreen.route + "?taskId=$taskId")
                        }
                    )
                }
            }
        },
        floatingActionButton = {
            ListFab(onFabClicked = {
                navController.navigate(Screen.TaskScreen.route)
            })
        }
    )
}

@Composable
fun SnackBar(
    scaffoldState: ScaffoldState,
    todoTask: TodoTask?,
    message: String,
    actionLabel: String,
    onUndoClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = todoTask) {
        scope.launch {
            val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel
            )
            if (snackBarResult == SnackbarResult.ActionPerformed) {
                onUndoClick()
            }
        }
    }
}


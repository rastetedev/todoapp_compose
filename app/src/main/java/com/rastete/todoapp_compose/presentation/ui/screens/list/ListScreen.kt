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
import com.rastete.todoapp_compose.domain.TodoTask
import com.rastete.todoapp_compose.presentation.ui.screens.Screen
import com.rastete.todoapp_compose.presentation.ui.screens.list.components.*
import com.rastete.todoapp_compose.presentation.util.Action
import com.rastete.todoapp_compose.presentation.util.RequestState
import com.rastete.todoapp_compose.presentation.util.SearchAppBarState
import com.rastete.todoapp_compose.presentation.viewmodel.ListViewModel
import kotlinx.coroutines.launch

@Composable
fun ListScreen(
    navController: NavController,
    action: Action,
    listViewModel: ListViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = true) {
        listViewModel.getList()
    }

    val tasksRequestState by listViewModel.tasks.collectAsState()
    val searchAppBarState: SearchAppBarState by listViewModel.searchAppBarState
    val searchTextState: String by listViewModel.searchTextState
    val scaffoldState = rememberScaffoldState()

    if (action == Action.DELETE) {
        SnackBar(
            scaffoldState = scaffoldState,
            message = stringResource(id = com.rastete.todoapp_compose.R.string.restore_task),
            actionLabel = stringResource(id = com.rastete.todoapp_compose.R.string.ok)
        ) {
            listViewModel.restoreTask()
        }
    }

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
                },
                onSortClick = {
                    listViewModel.persistSortingState(it)
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
    message: String,
    actionLabel: String,
    onUndoClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = true) {
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


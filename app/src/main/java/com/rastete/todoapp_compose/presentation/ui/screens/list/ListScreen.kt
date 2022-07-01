package com.rastete.todoapp_compose.presentation.ui.screens.list

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rastete.todoapp_compose.domain.TodoTask
import com.rastete.todoapp_compose.presentation.ui.screens.Screen
import com.rastete.todoapp_compose.presentation.ui.screens.list.components.*
import com.rastete.todoapp_compose.presentation.util.Action
import com.rastete.todoapp_compose.presentation.util.RequestState
import com.rastete.todoapp_compose.presentation.util.SearchAppBarState
import com.rastete.todoapp_compose.presentation.viewmodel.ListViewModel
import kotlinx.coroutines.CoroutineScope
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

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val tasksRequestState by listViewModel.tasks.collectAsState()
    val searchAppBarState: SearchAppBarState by listViewModel.searchAppBarState
    val searchTextState: String by listViewModel.searchTextState
    val scaffoldState = rememberScaffoldState()

    if (action == Action.DELETE) {
        SnackBar(
            scope = scope,
            scaffoldState = scaffoldState,
            context = context,
            listViewModel = listViewModel
        )
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
                        },
                        onSwipeToDelete = { todoTask ->
                            listViewModel.deleteTask(todoTask)
                            launchSnackBar(scope, scaffoldState, context, listViewModel)
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
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    context: Context,
    listViewModel: ListViewModel
) {
    LaunchedEffect(key1 = true) {
        launchSnackBar(scope, scaffoldState, context, listViewModel)
    }
}

fun launchSnackBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    context: Context,
    listViewModel: ListViewModel
) {
    scope.launch {
        val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
            message = context.getString(com.rastete.todoapp_compose.R.string.restore_task),
            actionLabel = context.getString(com.rastete.todoapp_compose.R.string.ok)
        )
        if (snackBarResult == SnackbarResult.ActionPerformed) {
            listViewModel.restoreTask()
        }
    }
}

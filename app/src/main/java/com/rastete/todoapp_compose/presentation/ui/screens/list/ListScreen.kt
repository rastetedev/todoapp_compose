package com.rastete.todoapp_compose.presentation.ui.screens.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.rastete.todoapp_compose.domain.TodoTask
import com.rastete.todoapp_compose.presentation.ui.screens.list.components.ListAppBar
import com.rastete.todoapp_compose.presentation.ui.screens.list.components.ListEmptyContent
import com.rastete.todoapp_compose.presentation.ui.screens.list.components.ListFab
import com.rastete.todoapp_compose.presentation.ui.screens.list.components.TodoItem
import com.rastete.todoapp_compose.presentation.util.RequestState
import com.rastete.todoapp_compose.presentation.util.SearchAppBarState
import com.rastete.todoapp_compose.presentation.viewmodel.TodoSharedViewModel

@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: TodoSharedViewModel
) {

    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTasks()
    }

    val tasksRequestState by sharedViewModel.tasks.collectAsState()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    Scaffold(
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
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
                        navigateToTaskScreen = navigateToTaskScreen
                    )
                }
            }
        },
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        }
    )
}


@Composable
fun ListContent(
    modifier: Modifier = Modifier,
    todoTaskList: List<TodoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(
            items = todoTaskList,
            key = { todoTask ->
                todoTask.id
            }
        ) { todoTask ->
            TodoItem(
                todoTask = todoTask,
                navigateToTaskScreen = navigateToTaskScreen
            )
        }
    }
}
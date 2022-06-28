package com.rastete.todoapp_compose.presentation.ui.screens.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.rastete.todoapp_compose.domain.TodoTask
import com.rastete.todoapp_compose.presentation.ui.screens.list.components.*
import com.rastete.todoapp_compose.presentation.util.RequestState
import com.rastete.todoapp_compose.presentation.util.SearchAppBarState
import com.rastete.todoapp_compose.presentation.viewmodel.ListViewModel

@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    listViewModel: ListViewModel
) {

    LaunchedEffect(key1 = true) {
        listViewModel.getAllTasks()
    }

    val tasksRequestState by listViewModel.tasks.collectAsState()
    val searchAppBarState: SearchAppBarState by listViewModel.searchAppBarState
    val searchTextState: String by listViewModel.searchTextState

    Scaffold(
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



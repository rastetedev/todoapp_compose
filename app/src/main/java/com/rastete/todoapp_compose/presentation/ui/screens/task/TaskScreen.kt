package com.rastete.todoapp_compose.presentation.ui.screens.task

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.rastete.todoapp_compose.domain.Priority
import com.rastete.todoapp_compose.presentation.ui.screens.task.components.TaskAppBar
import com.rastete.todoapp_compose.presentation.ui.screens.task.components.TaskContent
import com.rastete.todoapp_compose.presentation.util.Action
import com.rastete.todoapp_compose.presentation.viewmodel.TaskViewModel

@Composable
fun TaskScreen(
    taskViewModel: TaskViewModel,
    navigateToListScreen: (Action) -> Unit,
) {

    val titleState: String by taskViewModel.titleState
    val descriptionState: String by taskViewModel.descriptionState
    val priorityState: Priority by taskViewModel.priorityState

    Scaffold(
        topBar = {
            TaskAppBar(titleState, navigateToListScreen)
        },
        content = {
            TaskContent(
                modifier = Modifier.padding(it),
                title = titleState,
                onTitleChange = { title ->
                    taskViewModel.changeTitle(title)
                },
                description = descriptionState,
                onDescriptionChange = { description ->
                    taskViewModel.changeDescription(description)
                },
                priority = priorityState,
                onPrioritySelected = { priority ->
                    taskViewModel.changePriority(priority)
                }
            )
        }
    )
}
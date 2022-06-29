package com.rastete.todoapp_compose.presentation.ui.screens.task

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.rastete.todoapp_compose.R
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
    val idState: Int by taskViewModel.idState
    val titleState: String by taskViewModel.titleState
    val descriptionState: String by taskViewModel.descriptionState
    val priorityState: Priority by taskViewModel.priorityState

    val context = LocalContext.current
    Scaffold(
        topBar = {
            TaskAppBar(titleState, idState) { action ->
                if (action == Action.NO_ACTION) {
                    navigateToListScreen(action)
                } else {
                    if (taskViewModel.areValidFields()) {
                        navigateToListScreen(action)
                    } else {
                        showToast(context)
                    }
                }
            }
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

fun showToast(context: Context) {
    Toast.makeText(context, context.getString(R.string.empty_fields), Toast.LENGTH_SHORT).show()
}

package com.rastete.todoapp_compose.presentation.ui.screens.task.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.rastete.todoapp_compose.R
import com.rastete.todoapp_compose.presentation.ui.theme.topAppBarBackgroundColor
import com.rastete.todoapp_compose.presentation.ui.theme.topAppBarContentColor
import com.rastete.todoapp_compose.presentation.util.Action

@Composable
fun ExistingTaskAppBAr(
    title: String,
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            CloseAction(onCloseClick = navigateToListScreen)
        },
        title = {
            Text(
                text = title,
                color = MaterialTheme.colors.topAppBarContentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            DeleteTaskAction(onDeleteTaskClick = navigateToListScreen)
            UpdateTaskAction(onUpdateTaskClick = navigateToListScreen)
        }
    )
}

@Composable
fun CloseAction(onCloseClick: (Action) -> Unit) {
    IconButton(onClick = { onCloseClick(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(R.string.close_screen),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun DeleteTaskAction(onDeleteTaskClick: (Action) -> Unit) {
    IconButton(onClick = { onDeleteTaskClick(Action.DELETE) }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(R.string.delete_task),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun UpdateTaskAction(onUpdateTaskClick: (Action) -> Unit) {
    IconButton(onClick = { onUpdateTaskClick(Action.UPDATE) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(R.string.update_task),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}
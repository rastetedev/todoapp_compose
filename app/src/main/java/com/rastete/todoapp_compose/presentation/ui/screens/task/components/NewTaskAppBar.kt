package com.rastete.todoapp_compose.presentation.ui.screens.task.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.rastete.todoapp_compose.R
import com.rastete.todoapp_compose.presentation.ui.theme.topAppBarBackgroundColor
import com.rastete.todoapp_compose.presentation.ui.theme.topAppBarContentColor
import com.rastete.todoapp_compose.presentation.util.Action

@Composable
fun NewTaskAppBAr(
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            BackAction(onBackClick = navigateToListScreen)
        },
        title = {
            Text(
                text = stringResource(id = R.string.add_task),
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            AddTaskAction(navigateToListScreen)
        }
    )
}

@Composable
fun BackAction(onBackClick: (Action) -> Unit) {
    IconButton(onClick = { onBackClick(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(R.string.back_arrow),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}


@Composable
fun AddTaskAction(onAddAction: (Action) -> Unit) {
    IconButton(onClick = { onAddAction(Action.ADD) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(R.string.add_task),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}


package com.rastete.todoapp_compose.presentation.ui.screens.list.components

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.rastete.todoapp_compose.domain.TodoTask

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListContent(
    modifier: Modifier = Modifier,
    todoTaskList: List<TodoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    onSwipeToDelete: (TodoTask) -> Unit
) {

    LazyColumn(modifier = modifier) {
        items(
            items = todoTaskList,
            key = { todoTask ->
                todoTask.id ?: 0
            }
        ) { todoTask ->

            val dismissState = rememberDismissState(
                confirmStateChange = {
                    if (it == DismissValue.DismissedToStart) {
                        onSwipeToDelete(todoTask)
                    }
                    true
                }
            )

            val degrees by animateFloatAsState(
                targetValue = if (dismissState.targetValue == DismissValue.Default) 0f else -45f
            )

            var isVisible by remember {
                mutableStateOf(false)
            }

            LaunchedEffect(key1 = true) {
                isVisible = true
            }

            AnimatedVisibility(
                visible = isVisible,
                enter = expandVertically(
                    animationSpec = tween(300)
                ),
                exit = shrinkVertically(
                    animationSpec = tween(100)
                )
            ) {
                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    dismissThresholds = {
                        FractionalThreshold(0.2f)
                    },
                    background = {
                        RedBackground(degrees = degrees)
                    }
                ) {
                    TodoItem(todoTask = todoTask, navigateToTaskScreen = navigateToTaskScreen)
                }
            }

        }
    }
}
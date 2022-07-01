package com.rastete.todoapp_compose.presentation.util

import androidx.compose.ui.graphics.Color
import com.rastete.todoapp_compose.domain.Priority
import com.rastete.todoapp_compose.presentation.ui.theme.HighPriorityColor
import com.rastete.todoapp_compose.presentation.ui.theme.LowPriorityColor
import com.rastete.todoapp_compose.presentation.ui.theme.MediumPriorityColor
import com.rastete.todoapp_compose.presentation.ui.theme.NonePriorityColor
import java.lang.Exception

enum class Action {
    ADD,
    UPDATE,
    DELETE,
    DELETE_ALL,
    UNDO,
    NO_ACTION
}

fun String?.toAction(): Action {
    return try {
        if (this.isNullOrEmpty()) Action.NO_ACTION else Action.valueOf(this)
    } catch (e: Exception) {
        Action.NO_ACTION
    }
}

fun Priority.mapToColor(): Color {
    return when (this) {
        Priority.LOW -> LowPriorityColor
        Priority.MEDIUM -> MediumPriorityColor
        Priority.HIGH -> HighPriorityColor
        Priority.NONE -> NonePriorityColor
    }
}

enum class SearchAppBarState {
    OPENED,
    CLOSED
}
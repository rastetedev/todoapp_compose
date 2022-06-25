package com.rastete.todoapp_compose.presentation.ui.screens.list.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rastete.todoapp_compose.domain.Priority
import com.rastete.todoapp_compose.presentation.ui.theme.LARGE_SIZE
import com.rastete.todoapp_compose.presentation.ui.theme.PRIORITY_INDICATOR_SIZE
import com.rastete.todoapp_compose.presentation.ui.theme.Typography
import com.rastete.todoapp_compose.presentation.util.mapToColor

@Composable
fun PriorityItem(
    priority: Priority
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Canvas(modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)) {
            drawCircle(color = priority.mapToColor())
        }
        Spacer(modifier = Modifier.width(LARGE_SIZE))
        Text(
            text = priority.name,
            style = Typography.subtitle1,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
@Preview
fun PriorityItemPreview() {
    PriorityItem(priority = Priority.HIGH)
}
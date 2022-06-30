package com.rastete.todoapp_compose.presentation.ui.screens.list.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.rastete.todoapp_compose.R
import com.rastete.todoapp_compose.presentation.ui.theme.fabBackgroundColor

@Composable
fun ListFab(
    onFabClicked: () -> Unit
) {
    FloatingActionButton(
        onClick = {
            onFabClicked()
        },
        backgroundColor = MaterialTheme.colors.fabBackgroundColor
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_button),
            tint = Color.White
        )
    }
}
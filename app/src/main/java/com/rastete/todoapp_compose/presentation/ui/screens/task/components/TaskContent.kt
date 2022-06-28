package com.rastete.todoapp_compose.presentation.ui.screens.task.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rastete.todoapp_compose.R
import com.rastete.todoapp_compose.domain.Priority
import com.rastete.todoapp_compose.presentation.ui.screens.list.components.PriorityDropDown
import com.rastete.todoapp_compose.presentation.ui.theme.LARGE_SIZE
import com.rastete.todoapp_compose.presentation.ui.theme.MEDIUM_SIZE
import com.rastete.todoapp_compose.presentation.ui.theme.Typography

@Composable
fun TaskContent(
    modifier: Modifier = Modifier,
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(all = LARGE_SIZE)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = onTitleChange,
            label = {
                Text(text = stringResource(id = R.string.title))
            },
            textStyle = Typography.body1,
            singleLine = true
        )
        Spacer(modifier = Modifier.height(MEDIUM_SIZE))
        PriorityDropDown(
            priority = priority,
            onPrioritySelected = onPrioritySelected
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = description,
            onValueChange = onDescriptionChange,
            label = {
                Text(text = stringResource(id = R.string.description))
            },
            textStyle = Typography.body1
        )

    }
}

@Composable
@Preview
fun TaskContentPreview() {
    TaskContent(
        title = "Title",
        onTitleChange = {},
        description = "Description",
        onDescriptionChange = {},
        priority = Priority.MEDIUM,
        onPrioritySelected = {}
    )
}
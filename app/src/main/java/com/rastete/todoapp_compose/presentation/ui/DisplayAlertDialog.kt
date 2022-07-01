package com.rastete.todoapp_compose.presentation.ui

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.rastete.todoapp_compose.R
import com.rastete.todoapp_compose.presentation.ui.theme.Typography

@Composable
fun DisplayAlertDialog(
    title: String,
    message: String,
    openDialog: Boolean,
    closeDialog: () -> Unit,
    onYesClick: () -> Unit
) {
    if (openDialog) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                    fontSize = Typography.h5.fontSize,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = message,
                    fontSize = Typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal
                )
            },
            onDismissRequest = { closeDialog() },
            confirmButton = {
                Button(onClick = {
                    onYesClick()
                    closeDialog()
                }) {
                    Text(text = stringResource(id = R.string.yes))
                }
            },
            dismissButton = {
                OutlinedButton(onClick = {
                    closeDialog()
                }) {
                    Text(text = stringResource(id = R.string.no))
                }
            }
        )
    }
}
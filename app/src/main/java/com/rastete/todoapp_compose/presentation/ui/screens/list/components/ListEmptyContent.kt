package com.rastete.todoapp_compose.presentation.ui.screens.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.rastete.todoapp_compose.R
import com.rastete.todoapp_compose.presentation.ui.theme.EMPTY_ICON_SIZE
import com.rastete.todoapp_compose.presentation.ui.theme.MediumGray

@Composable
fun ListEmptyContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(EMPTY_ICON_SIZE),
            painter = painterResource(id = R.drawable.ic_sad_face),
            contentDescription = stringResource(
                id = R.string.sad_face
            ),
            tint = MediumGray
        )
        Text(
            text = stringResource(id = R.string.no_tasks),
            color = MediumGray,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h6.fontSize
        )
    }
}
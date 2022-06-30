package com.rastete.todoapp_compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.rastete.todoapp_compose.presentation.navigation.SetupNavigation
import com.rastete.todoapp_compose.presentation.ui.theme.TodoAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppComposeTheme {
                val navController = rememberNavController()
                SetupNavigation(navHostController = navController)
            }
        }
    }
}

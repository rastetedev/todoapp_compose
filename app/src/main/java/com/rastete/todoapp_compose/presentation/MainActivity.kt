package com.rastete.todoapp_compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rastete.todoapp_compose.presentation.navigation.SetupNavigation
import com.rastete.todoapp_compose.presentation.ui.theme.TodoAppComposeTheme
import com.rastete.todoapp_compose.presentation.viewmodel.ListViewModel
import com.rastete.todoapp_compose.presentation.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController
    private val taskViewModel: TaskViewModel by viewModels()
    private val listViewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppComposeTheme {
                navHostController = rememberNavController()
                SetupNavigation(
                    navHostController = navHostController,
                    listViewModel = listViewModel,
                    taskViewModel = taskViewModel,

                    )
            }
        }
    }
}

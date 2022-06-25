package com.rastete.todoapp_compose.domain

data class TodoTask(
    val id: Int,
    val title: String,
    val description: String,
    val priority: Priority
)

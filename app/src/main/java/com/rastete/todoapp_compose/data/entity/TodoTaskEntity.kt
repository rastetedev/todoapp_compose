package com.rastete.todoapp_compose.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoTaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val priority: String,
    val isDisabled: Boolean = false
)

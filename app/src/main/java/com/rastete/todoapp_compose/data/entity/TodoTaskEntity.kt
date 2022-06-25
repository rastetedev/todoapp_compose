package com.rastete.todoapp_compose.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rastete.todoapp_compose.domain.Priority
import com.rastete.todoapp_compose.domain.TodoTask

@Entity
data class TodoTaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val priority: String
) {

    fun toDomain(): TodoTask {
        return TodoTask(
            id = id,
            title = title,
            description = description,
            priority = Priority.valueOf(priority)
        )
    }

    companion object {

        fun toEntity(todoTask: TodoTask): TodoTaskEntity {
            return TodoTaskEntity(
                id = todoTask.id,
                title = todoTask.title,
                description = todoTask.description,
                priority = todoTask.priority.toString()
            )
        }
    }
}

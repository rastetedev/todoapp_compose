package com.rastete.todoapp_compose.data.mapper

import com.rastete.todoapp_compose.data.entity.TodoTaskEntity
import com.rastete.todoapp_compose.domain.Priority
import com.rastete.todoapp_compose.domain.TodoTask

class TodoTaskMapper {

    fun fromEntityToDomain(todoTaskEntity: TodoTaskEntity) =
        TodoTask(
            id = todoTaskEntity.id,
            title = todoTaskEntity.title,
            description = todoTaskEntity.description,
            priority = Priority.valueOf(todoTaskEntity.priority)
        )

    fun fromDomainToEntity(todoTask: TodoTask) =
        TodoTaskEntity(
            id = todoTask.id ?: 0,
            title = todoTask.title,
            description = todoTask.description,
            priority = todoTask.priority.toString()
        )
}

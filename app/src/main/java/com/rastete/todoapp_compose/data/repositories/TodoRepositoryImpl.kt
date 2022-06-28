package com.rastete.todoapp_compose.data.repositories

import com.rastete.todoapp_compose.data.TodoDao
import com.rastete.todoapp_compose.data.entity.TodoTaskEntity
import com.rastete.todoapp_compose.domain.TodoTask
import com.rastete.todoapp_compose.domain.repository.TodoRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class TodoRepositoryImpl @Inject constructor(private val todoDao: TodoDao) : TodoRepository {

    override fun getAllTasks(): Flow<List<TodoTask>> {
        return todoDao.getAllTasks().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun filterTasks(query: String): Flow<List<TodoTask>> {
        return todoDao.filterTasks(query).map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun getTaskById(todoTaskId: Int): Flow<TodoTask?> {
        if (todoTaskId == -1) {
            return flow { emit(null) }
        }
        return todoDao.getTaskById(todoTaskId).map { it.toDomain() }
    }

    override suspend fun addTask(todoTask: TodoTask) {
        todoDao.insertTask(TodoTaskEntity.toEntity(todoTask))
    }

    override suspend fun deleteTask(todoTask: TodoTask) {
        todoDao.deleteTask(TodoTaskEntity.toEntity(todoTask))
    }

    override suspend fun updateTask(todoTask: TodoTask) {
        todoDao.updateTask(TodoTaskEntity.toEntity(todoTask))
    }

    override suspend fun deleteAllTasks() {
        todoDao.deleteAllTasks()
    }
}
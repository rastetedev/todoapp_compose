package com.rastete.todoapp_compose.data.repositories

import com.rastete.todoapp_compose.data.TodoDao
import com.rastete.todoapp_compose.data.mapper.TodoTaskMapper
import com.rastete.todoapp_compose.domain.TodoTask
import com.rastete.todoapp_compose.domain.repository.TodoRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class TodoRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao,
    private val todoTaskMapper: TodoTaskMapper
) : TodoRepository {

    override fun getAllTasks(): Flow<List<TodoTask>> {
        return todoDao.getAllTasks().map { list ->
            list.map {
                todoTaskMapper.fromEntityToDomain(it)
            }
        }
    }

    override suspend fun filterTasks(query: String): Flow<List<TodoTask>> {
        return todoDao.filterTasks(query).map { list ->
            list.map {
                todoTaskMapper.fromEntityToDomain(it)
            }
        }
    }

    override suspend fun getTaskById(todoTaskId: Int): Flow<TodoTask> {
        return todoDao.getTaskById(todoTaskId).map { todoTaskEntity ->
            todoTaskMapper.fromEntityToDomain(todoTaskEntity)
        }
    }

    override suspend fun addTask(todoTask: TodoTask) {
        todoDao.insertTask(todoTaskMapper.fromDomainToEntity(todoTask))
    }

    override suspend fun deleteTask(taskId: Int) {
        todoDao.delete(taskId = taskId)
    }

    override suspend fun updateTask(todoTask: TodoTask) {
        todoDao.updateTask(todoTaskMapper.fromDomainToEntity(todoTask))
    }

    override suspend fun deleteAllTasks() {
        todoDao.deleteAllTasks()
    }

    override suspend fun restoreLastDeletedTask() {
        todoDao.restoreTask()
    }

    override suspend fun sortByLowPriority(): Flow<List<TodoTask>> {
        return todoDao.sortByLowPriority().map { list ->
            list.map {
                todoTaskMapper.fromEntityToDomain(it)
            }
        }
    }

    override suspend fun sortByHighPriority(): Flow<List<TodoTask>> {
        return todoDao.sortByHighPriority().map { list ->
            list.map {
                todoTaskMapper.fromEntityToDomain(it)
            }
        }
    }
}
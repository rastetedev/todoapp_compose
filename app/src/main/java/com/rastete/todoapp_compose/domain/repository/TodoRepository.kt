package com.rastete.todoapp_compose.domain.repository

import com.rastete.todoapp_compose.domain.TodoTask
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun getTaskById(todoTaskId: Int): Flow<TodoTask>

    suspend fun filterTasks(query: String): Flow<List<TodoTask>>

    suspend fun addTask(todoTask: TodoTask)

    suspend fun deleteTask(taskId: Int)

    suspend fun updateTask(todoTask: TodoTask)

    suspend fun restoreLastDeletedTask()

    suspend fun deleteAllTasks()

    fun sortByDefault(): Flow<List<TodoTask>>

    fun sortByLowPriority(): Flow<List<TodoTask>>

    fun sortByHighPriority(): Flow<List<TodoTask>>
}
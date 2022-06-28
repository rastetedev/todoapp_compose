package com.rastete.todoapp_compose.domain.repository

import com.rastete.todoapp_compose.domain.TodoTask
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun getAllTasks(): Flow<List<TodoTask>>

    suspend fun getTaskById(todoTaskId: Int): Flow<TodoTask?>

    suspend fun filterTasks(query: String): Flow<List<TodoTask>>

    suspend fun addTask(todoTask: TodoTask)

    suspend fun deleteTask(todoTask: TodoTask)

    suspend fun updateTask(todoTask: TodoTask)

    suspend fun deleteAllTasks()
}
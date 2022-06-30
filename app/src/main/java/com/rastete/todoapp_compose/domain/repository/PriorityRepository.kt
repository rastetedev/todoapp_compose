package com.rastete.todoapp_compose.domain.repository

import com.rastete.todoapp_compose.domain.Priority
import kotlinx.coroutines.flow.Flow


interface PriorityRepository {

    suspend fun savePriorityState(priority: Priority)

    fun getPriorityState(): Flow<String>
}
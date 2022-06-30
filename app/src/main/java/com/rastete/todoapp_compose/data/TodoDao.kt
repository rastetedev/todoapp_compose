package com.rastete.todoapp_compose.data

import androidx.room.*
import com.rastete.todoapp_compose.data.entity.TodoTaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM TodoTaskEntity ORDER BY id ASC")
    fun getAllTasks(): Flow<List<TodoTaskEntity>>

    @Query("SELECT * FROM TodoTaskEntity WHERE id = :todoTaskId")
    fun getTaskById(todoTaskId: Int): Flow<TodoTaskEntity>

    @Query("SELECT * FROM TodoTaskEntity WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun filterTasks(query: String): Flow<List<TodoTaskEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: TodoTaskEntity)

    @Update
    suspend fun updateTask(task: TodoTaskEntity)

    @Query("UPDATE TodoTaskEntity SET isDisabled = :isDisabled WHERE id = :taskId")
    suspend fun deleteTask(taskId: Int, isDisabled: Boolean = false)

    @Query("DELETE FROM TodoTaskEntity")
    suspend fun deleteAllTasks()

}
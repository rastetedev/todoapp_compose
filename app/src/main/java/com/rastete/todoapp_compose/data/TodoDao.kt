package com.rastete.todoapp_compose.data

import androidx.room.*
import com.rastete.todoapp_compose.data.entity.TodoTaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM TodoTaskEntity WHERE isDisabled = 0 ORDER BY id ASC ")
    fun getAllTasks(): Flow<List<TodoTaskEntity>>

    @Query("SELECT * FROM TodoTaskEntity WHERE id = :todoTaskId")
    fun getTaskById(todoTaskId: Int): Flow<TodoTaskEntity>

    @Query("SELECT * FROM TodoTaskEntity WHERE isDisabled = 0 AND title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun filterTasks(query: String): Flow<List<TodoTaskEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: TodoTaskEntity)

    @Update
    suspend fun updateTask(task: TodoTaskEntity)

    @Query("DELETE FROM TodoTaskEntity WHERE isDisabled = 1")
    suspend fun deletePermanent()

    @Query("UPDATE TodoTaskEntity SET isDisabled = 1 WHERE id = :taskId")
    suspend fun disableTask(taskId: Int)

    @Query("UPDATE TodoTaskEntity SET isDisabled = 0 WHERE isDisabled = 1")
    suspend fun restoreTask()

    @Query("DELETE FROM TodoTaskEntity")
    suspend fun deleteAllTasks()

    @Transaction
    suspend fun delete(taskId: Int) {
        deletePermanent()
        disableTask(taskId)
    }

    @Query(
        """
        SELECT * FROM TodoTaskEntity 
        WHERE isDisabled = 0 
        ORDER BY 
        CASE 
            WHEN priority LIKE 'L%' THEN 1
            WHEN priority LIKE 'M%' THEN 2
            WHEN priority LIKE 'H%' THEN 3 
        END
        """
    )
    fun sortByLowPriority(): Flow<List<TodoTaskEntity>>

    @Query(
        """
        SELECT * FROM TodoTaskEntity 
        WHERE isDisabled = 0 
        ORDER BY 
        CASE 
            WHEN priority LIKE 'H%' THEN 1
            WHEN priority LIKE 'M%' THEN 2
            WHEN priority LIKE 'L%' THEN 3 
        END
        """
    )
    fun sortByHighPriority(): Flow<List<TodoTaskEntity>>

}
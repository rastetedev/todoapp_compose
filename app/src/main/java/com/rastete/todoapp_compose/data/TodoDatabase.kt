package com.rastete.todoapp_compose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rastete.todoapp_compose.data.entity.TodoTaskEntity

@Database(entities = [TodoTaskEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {

    abstract val todoDao: TodoDao
}
package com.rastete.todoapp_compose.di

import android.app.Application
import androidx.room.Room
import com.rastete.todoapp_compose.data.TodoDao
import com.rastete.todoapp_compose.data.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesTodoDatabase(app: Application): TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            "todoApp.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesTodoDao(todoDatabase: TodoDatabase): TodoDao {
        return todoDatabase.todoDao
    }

}
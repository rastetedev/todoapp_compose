package com.rastete.todoapp_compose.di

import com.rastete.todoapp_compose.data.repositories.PriorityRepositoryImpl
import com.rastete.todoapp_compose.data.repositories.TodoRepositoryImpl
import com.rastete.todoapp_compose.domain.repository.PriorityRepository
import com.rastete.todoapp_compose.domain.repository.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class TodoModule {

    @Binds
    abstract fun bindsTodoRepository(todoRepository: TodoRepositoryImpl): TodoRepository

    @Binds
    abstract fun bindsPriorityRepository(priorityRepository: PriorityRepositoryImpl): PriorityRepository
}
package com.rastete.todoapp_compose.di

import com.rastete.todoapp_compose.data.mapper.TodoTaskMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object MappersModule {

    @Provides
    fun providesTodoTaskMapper(): TodoTaskMapper = TodoTaskMapper()
}
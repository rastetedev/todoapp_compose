package com.rastete.todoapp_compose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastete.todoapp_compose.domain.TodoTask
import com.rastete.todoapp_compose.domain.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val todoRepository: TodoRepository) :
    ViewModel() {

    private var todoTaskRecentlyDeleted: TodoTask? = null



    fun restoreTask() {
        viewModelScope.launch {
            todoRepository.addTask(todoTaskRecentlyDeleted ?: return@launch)
            todoTaskRecentlyDeleted = null
        }
    }

    fun getTodoTask() = todoTaskRecentlyDeleted

}
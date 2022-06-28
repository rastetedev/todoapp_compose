package com.rastete.todoapp_compose.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastete.todoapp_compose.domain.Priority
import com.rastete.todoapp_compose.domain.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val todoRepository: TodoRepository) :
    ViewModel() {

    var idState = mutableStateOf(0)
    var titleState = mutableStateOf("")
    var descriptionState = mutableStateOf("")
    var priorityState = mutableStateOf(Priority.NONE)

    fun getSelectedTask(taskId: Int?) {
        viewModelScope.launch {
            taskId?.let { id ->
                todoRepository.getTaskById(id).collect { todoTask ->
                    if (todoTask != null) {
                        idState.value = todoTask.id
                        titleState.value = todoTask.title
                        descriptionState.value = todoTask.description
                        priorityState.value = todoTask.priority
                    } else {
                        idState.value = 0
                        titleState.value = ""
                        descriptionState.value = ""
                        priorityState.value = Priority.LOW
                    }
                }
            }
        }
    }

    fun changeTitle(title: String) {
        titleState.value = title
    }

    fun changeDescription(description: String) {
        descriptionState.value = description
    }

    fun changePriority(priority: Priority) {
        priorityState.value = priority
    }
}
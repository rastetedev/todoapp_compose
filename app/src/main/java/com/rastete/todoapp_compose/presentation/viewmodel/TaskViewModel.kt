package com.rastete.todoapp_compose.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastete.todoapp_compose.domain.Priority
import com.rastete.todoapp_compose.domain.TodoTask
import com.rastete.todoapp_compose.domain.repository.TodoRepository
import com.rastete.todoapp_compose.presentation.util.Constants.DEFAULT_NO_TASK_ID
import com.rastete.todoapp_compose.presentation.util.Constants.MAX_TITLE_LENGTH
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val todoRepository: TodoRepository) :
    ViewModel() {

    var idState = mutableStateOf(DEFAULT_NO_TASK_ID)
    var titleState = mutableStateOf("")
    var descriptionState = mutableStateOf("")
    var priorityState = mutableStateOf(Priority.NONE)

    fun getSelectedTask(taskId: Int?) {
        viewModelScope.launch {
            taskId?.let { id ->
                todoRepository.getTaskById(id).collect { todoTask ->
                    if (todoTask != null) {
                        idState.value = todoTask.id ?: DEFAULT_NO_TASK_ID
                        titleState.value = todoTask.title
                        descriptionState.value = todoTask.description
                        priorityState.value = todoTask.priority
                    } else {
                        idState.value = DEFAULT_NO_TASK_ID
                        titleState.value = ""
                        descriptionState.value = ""
                        priorityState.value = Priority.LOW
                    }
                }
            }
        }
    }

    fun changeTitle(title: String) {
        if (title.length <= MAX_TITLE_LENGTH) {
            titleState.value = title
        }
    }

    fun changeDescription(description: String) {
        descriptionState.value = description
    }

    fun changePriority(priority: Priority) {
        priorityState.value = priority
    }

    fun areValidFields(): Boolean =
        titleState.value.isNotEmpty() && descriptionState.value.isNotEmpty()

    fun addTask() {
        viewModelScope.launch {
            todoRepository.addTask(
                TodoTask(
                    title = titleState.value,
                    description = descriptionState.value,
                    priority = priorityState.value
                )
            )
        }
    }

    fun updateTask() {
        viewModelScope.launch {
            todoRepository.updateTask(
                TodoTask(
                    id = idState.value,
                    title = titleState.value,
                    description = descriptionState.value,
                    priority = priorityState.value
                )
            )
        }
    }

    fun deleteTask() {
        viewModelScope.launch {
            todoRepository.deleteTask(
                TodoTask(
                    id = idState.value,
                    title = titleState.value,
                    description = descriptionState.value,
                    priority = priorityState.value
                )
            )
        }
    }
}
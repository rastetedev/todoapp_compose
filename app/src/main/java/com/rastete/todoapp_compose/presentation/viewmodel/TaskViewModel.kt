package com.rastete.todoapp_compose.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastete.todoapp_compose.domain.Priority
import com.rastete.todoapp_compose.domain.TodoTask
import com.rastete.todoapp_compose.domain.repository.TodoRepository
import com.rastete.todoapp_compose.presentation.ui.screens.Screen
import com.rastete.todoapp_compose.presentation.util.Constants.MAX_TITLE_LENGTH
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _taskScreenState = mutableStateOf(
        TaskScreenState()
    )
    val taskScreenState: State<TaskScreenState> = _taskScreenState

    init {
        savedStateHandle.get<Int>(Screen.TASK_ID_ARGUMENT_KEY)?.let { taskId ->
            if (taskId != Screen.DEFAULT_TASK_ID_ARGUMENT_VALUE) {
                getSelectedTask(taskId)
            }
        }
    }

    private fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
            todoRepository.getTaskById(taskId).collect { todoTask ->
                _taskScreenState.value = taskScreenState.value.copy(
                    id = taskId,
                    title = todoTask.title,
                    description = todoTask.description,
                    priority = todoTask.priority
                )
            }
        }
    }

    fun changeTitle(title: String) {
        if (title.length <= MAX_TITLE_LENGTH) {
            _taskScreenState.value = taskScreenState.value.copy(
                title = title
            )
        }
    }

    fun changeDescription(description: String) {
        _taskScreenState.value = taskScreenState.value.copy(
            description = description
        )
    }

    fun changePriority(priority: Priority) {
        _taskScreenState.value = taskScreenState.value.copy(
            priority = priority
        )
    }

    fun areValidFields(): Boolean =
        taskScreenState.value.title.isNotEmpty() && taskScreenState.value.description.isNotEmpty()

    fun addTask() {
        viewModelScope.launch {
            todoRepository.addTask(
                TodoTask(
                    title = taskScreenState.value.title,
                    description = taskScreenState.value.description,
                    priority = taskScreenState.value.priority
                )
            )
        }
    }

    fun updateTask() {
        viewModelScope.launch {
            todoRepository.updateTask(
                TodoTask(
                    id = taskScreenState.value.id,
                    title = taskScreenState.value.title,
                    description = taskScreenState.value.description,
                    priority = taskScreenState.value.priority
                )
            )
        }
    }

    fun deleteTask() {
        viewModelScope.launch {
            todoRepository.deleteTask(
                taskId = taskScreenState.value.id
            )
        }
    }
}

data class TaskScreenState(
    val id: Int = Screen.DEFAULT_TASK_ID_ARGUMENT_VALUE,
    val title: String = "",
    val description: String = "",
    val priority: Priority = Priority.LOW
)
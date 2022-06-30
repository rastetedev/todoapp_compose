package com.rastete.todoapp_compose.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastete.todoapp_compose.domain.Priority
import com.rastete.todoapp_compose.domain.TodoTask
import com.rastete.todoapp_compose.domain.repository.PriorityRepository
import com.rastete.todoapp_compose.domain.repository.TodoRepository
import com.rastete.todoapp_compose.presentation.util.RequestState
import com.rastete.todoapp_compose.presentation.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
    private val priorityRepository: PriorityRepository
) : ViewModel() {

    var tasks = MutableStateFlow<RequestState<List<TodoTask>>>(RequestState.Idle)
        private set

    var persistState = MutableStateFlow<RequestState<Priority>>(RequestState.Idle)
        private set

    val searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)

    val searchTextState: MutableState<String> = mutableStateOf("")


    fun changeSearchAppBarState(state: SearchAppBarState) {
        searchAppBarState.value = state
        if (state == SearchAppBarState.CLOSED) {
            sortBy((persistState.value as RequestState.Success).data)
        }
    }

    fun changeSearchText(searchText: String) {
        searchTextState.value = searchText
    }

    fun search() {
        tasks.value = RequestState.Loading
        viewModelScope.launch {
            try {
                todoRepository.filterTasks(searchTextState.value).collect {
                    tasks.value = RequestState.Success(it)
                }
            } catch (e: Exception) {
                tasks.value = RequestState.Error(e)
            }
        }
    }

    fun deleteAllTasks() {
        viewModelScope.launch {
            todoRepository.deleteAllTasks()
        }
    }

    fun restoreTask() {
        viewModelScope.launch {
            todoRepository.restoreLastDeletedTask()
        }
    }

    fun persistSortingState(priority: Priority) {
        viewModelScope.launch(Dispatchers.IO) {
            priorityRepository.savePriorityState(priority)
        }
    }

    fun getList() {
        persistState.value = RequestState.Loading
        try {
            viewModelScope.launch {
                priorityRepository.getPriorityState().map {
                    Priority.valueOf(it)
                }.collect { priority ->
                    persistState.value = RequestState.Success(priority)
                    sortBy(priority)
                }
            }
        } catch (e: Exception) {
            persistState.value = RequestState.Error(e)
        }
    }

    private fun sortBy(priority: Priority) {
        when (priority) {
            Priority.LOW -> sortByLowPriority()
            Priority.HIGH -> sortByHighPriority()
            else -> sortByDefault()
        }
    }

    private fun sortByLowPriority() {
        tasks.value = RequestState.Loading
        viewModelScope.launch {
            try {
                todoRepository.sortByLowPriority().collect {
                    tasks.value = RequestState.Success(it)
                }
            } catch (e: Exception) {
                tasks.value = RequestState.Error(e)
            }
        }
    }

    private fun sortByHighPriority() {
        tasks.value = RequestState.Loading
        viewModelScope.launch {
            try {
                todoRepository.sortByHighPriority().collect {
                    tasks.value = RequestState.Success(it)
                }
            } catch (e: Exception) {
                tasks.value = RequestState.Error(e)
            }
        }
    }

    private fun sortByDefault() {
        tasks.value = RequestState.Loading
        viewModelScope.launch {
            try {
                todoRepository.sortByDefault().collect {
                    tasks.value = RequestState.Success(it)
                }
            } catch (e: Exception) {
                tasks.value = RequestState.Error(e)
            }
        }
    }

}
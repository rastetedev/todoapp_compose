package com.rastete.todoapp_compose.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastete.todoapp_compose.domain.TodoTask
import com.rastete.todoapp_compose.domain.repository.TodoRepository
import com.rastete.todoapp_compose.presentation.util.RequestState
import com.rastete.todoapp_compose.presentation.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val todoRepository: TodoRepository) :
    ViewModel() {

    var tasks = MutableStateFlow<RequestState<List<TodoTask>>>(RequestState.Idle)
        private set

    val searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)

    val searchTextState: MutableState<String> = mutableStateOf("")

    fun getAllTasks() {
        tasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                todoRepository.getAllTasks().collect {
                    tasks.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            tasks.value = RequestState.Error(e)
        }
    }

    fun changeSearchAppBarState(state: SearchAppBarState) {
        searchAppBarState.value = state
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

}
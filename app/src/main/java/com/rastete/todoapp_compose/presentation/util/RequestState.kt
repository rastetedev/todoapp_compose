package com.rastete.todoapp_compose.presentation.util

sealed class RequestState<out T> {

    object Idle : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()
    data class Success<T>(val data: T) : RequestState<T>()
    data class Error(val error: Throwable) : RequestState<Nothing>()

}

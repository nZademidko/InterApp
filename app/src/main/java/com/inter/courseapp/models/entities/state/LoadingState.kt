package com.inter.courseapp.models.entities.state

sealed class LoadingState<out T> {

    class Success<T>(data: T) : LoadingState<T>()
    class Error(throwable: Throwable) : LoadingState<Nothing>()
    object Loading : LoadingState<Nothing>()

}
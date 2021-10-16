package com.inter.courseapp.models.entities.state

sealed class Resource<out T> {

    class Success<T>(val data: T) : Resource<T>()
    class Error(val throwable: Throwable) : Resource<Nothing>()
    object Loading : Resource<Nothing>()

}
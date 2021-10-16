package com.inter.courseapp.models.entities.state

sealed class FoodListState<out T> {

    class Success<T>(val data: T) : FoodListState<T>()
    class Error(val message: String) : FoodListState<Nothing>()
    object Loading : FoodListState<Nothing>()

}
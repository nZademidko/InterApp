package com.inter.courseapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inter.courseapp.*
import com.inter.courseapp.models.entities.foodRecipe.FoodRecipe
import com.inter.courseapp.models.entities.state.FoodListState
import com.inter.courseapp.models.entities.state.Resource
import com.inter.courseapp.models.usecases.GetFoodRecipesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FoodRecipesViewModel @Inject constructor(
    private val getFoodRecipesUseCase: GetFoodRecipesUseCase
) : ViewModel() {


    private val _listRecipesState: MutableStateFlow<FoodListState<List<FoodRecipe>>> =
        MutableStateFlow(FoodListState.Loading)
    val listRecipesState get() = _listRecipesState.asStateFlow()

    fun loadRecipes(queries: HashMap<String, String>) =
        getFoodRecipesUseCase(queries = queries).onEach { resource ->

            when (resource) {

                is Resource.Loading ->
                    _listRecipesState.value = FoodListState.Loading

                is Resource.Error -> {
                    if (resource.throwable.message.toString().contains("timeout")) {
                        _listRecipesState.value = FoodListState.Error(message = "Time Out")
                    } else {
                        _listRecipesState.value =
                            FoodListState.Error(message = resource.throwable.message.toString())
                    }
                }

                is Resource.Success ->
                    _listRecipesState.value = FoodListState.Success(data = resource.data)
            }

        }.launchIn(viewModelScope)

    fun applyQueries() =
        hashMapOf(
            QUERY_NUMBER to "50",
            QUERY_API_KEY to API_KEY,
            QUERY_TYPE to "snack",
            QUERY_DIET to "diet",
            QUERY_ADD_RECIPE_INFORMATION to "true",
            QUERY_FILL_INGREDIENTS to "true",
        )
}
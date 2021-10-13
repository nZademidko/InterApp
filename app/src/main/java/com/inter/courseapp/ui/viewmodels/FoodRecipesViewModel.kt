package com.inter.courseapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foody.models.FoodRecipe
import com.inter.courseapp.*
import com.inter.courseapp.models.entities.state.LoadingState
import com.inter.courseapp.models.usecases.GetFoodRecipesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FoodRecipesViewModel @Inject constructor(
    private val getFoodRecipesUseCase: GetFoodRecipesUseCase
) : ViewModel() {


    private val _listRecipesState: MutableStateFlow<LoadingState<FoodRecipe>> =
        MutableStateFlow(LoadingState.Loading)
    val listRecipesState get() = _listRecipesState.asStateFlow()

    fun loadRecipes(queries: HashMap<String, String>) = getFoodRecipesUseCase(queries = queries)

    fun applyQueries(): HashMap<String, String> = hashMapOf(
        QUERY_NUMBER to "50",
        QUERY_API_KEY to API_KEY,
        QUERY_TYPE to "snack",
        QUERY_DIET to "vegan",
        QUERY_ADD_RECIPE_INFORMATION to "true",
        QUERY_FILL_INGREDIENTS to "true"
    )
}
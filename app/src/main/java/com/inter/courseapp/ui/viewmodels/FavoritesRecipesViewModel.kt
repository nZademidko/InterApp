package com.inter.courseapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inter.courseapp.models.entities.database.FoodRecipeEntity
import com.inter.courseapp.models.entities.foodRecipe.FoodRecipe
import com.inter.courseapp.models.entities.state.FoodListState
import com.inter.courseapp.models.entities.state.Resource
import com.inter.courseapp.models.usecases.DeleteFoodRecipeUseCase
import com.inter.courseapp.models.usecases.GetFavoritesRecipesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FavoritesRecipesViewModel @Inject constructor(
    private val getFavoritesRecipesUseCase: GetFavoritesRecipesUseCase,
    private val deleteFoodRecipeUseCase: DeleteFoodRecipeUseCase
) : ViewModel() {


    private val _listRecipesState: MutableStateFlow<FoodListState<List<FoodRecipeEntity>>> =
        MutableStateFlow(FoodListState.Loading)
    val listRecipesState get() = _listRecipesState.asStateFlow()

    private val _deleteRecipeState: MutableStateFlow<Resource<String>> =
        MutableStateFlow(Resource.Loading)
    val deleteRecipeState get() = _deleteRecipeState.asStateFlow()


    fun loadRecipes() {
        getFavoritesRecipesUseCase().onEach { resource ->

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
    }

    fun deleteRecipe(foodRecipeEntity: FoodRecipeEntity) {

        deleteFoodRecipeUseCase(foodRecipeEntity = foodRecipeEntity).onEach { resource ->

            when (resource) {

                is Resource.Loading ->
                    _deleteRecipeState.value = Resource.Loading

                is Resource.Error ->
                    _deleteRecipeState.value = Resource.Error(resource.throwable)

                is Resource.Success -> {
                    _deleteRecipeState.value = Resource.Success(data = resource.data)
                    _deleteRecipeState.value = Resource.Loading
                }
            }
        }.launchIn(viewModelScope)
    }
}
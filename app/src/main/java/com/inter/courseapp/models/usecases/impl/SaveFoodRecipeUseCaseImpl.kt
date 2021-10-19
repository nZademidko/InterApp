package com.inter.courseapp.models.usecases.impl

import com.inter.courseapp.models.entities.database.FoodRecipeEntity
import com.inter.courseapp.models.entities.foodRecipe.FoodRecipe
import com.inter.courseapp.models.entities.state.Resource
import com.inter.courseapp.models.repositories.FoodRecipeRepository
import com.inter.courseapp.models.usecases.SaveFoodRecipeUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveFoodRecipeUseCaseImpl @Inject constructor(
    private val repository: FoodRecipeRepository
) : SaveFoodRecipeUseCase {
    override fun invoke(foodRecipe: FoodRecipe) = flow {

        emit(Resource.Loading)
        repository.saveFoodRecipe(foodRecipe)
        emit(Resource.Success(data = "Success"))

    }.catch { throwable ->
        emit(Resource.Error(throwable = throwable))
    }
}
package com.inter.courseapp.models.usecases.impl

import com.inter.courseapp.models.entities.database.FoodRecipeEntity
import com.inter.courseapp.models.entities.state.Resource
import com.inter.courseapp.models.repositories.FavoritesRecipesRepository
import com.inter.courseapp.models.usecases.DeleteFoodRecipeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteFoodRecipeUseCaseImpl @Inject constructor(
    private val favoritesRecipesRepository: FavoritesRecipesRepository
) : DeleteFoodRecipeUseCase {
    override fun invoke(foodRecipeEntity: FoodRecipeEntity): Flow<Resource<String>> = flow {

        emit(Resource.Loading)
        favoritesRecipesRepository.deleteRecipe(foodRecipeEntity = foodRecipeEntity)
        emit(Resource.Success("Success"))
    }.catch { throwable ->
        emit(Resource.Error(throwable = throwable))
    }
}
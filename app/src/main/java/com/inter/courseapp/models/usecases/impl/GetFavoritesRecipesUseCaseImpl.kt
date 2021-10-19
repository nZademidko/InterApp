package com.inter.courseapp.models.usecases.impl

import com.inter.courseapp.models.entities.database.FoodRecipeEntity
import com.inter.courseapp.models.entities.foodRecipe.FoodRecipe
import com.inter.courseapp.models.entities.state.Resource
import com.inter.courseapp.models.repositories.FavoritesRecipesRepository
import com.inter.courseapp.models.usecases.GetFavoritesRecipesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoritesRecipesUseCaseImpl @Inject constructor(
    private val repository: FavoritesRecipesRepository
) : GetFavoritesRecipesUseCase {

    override fun invoke(): Flow<Resource<List<FoodRecipeEntity>>> = flow {

        emit(Resource.Loading)
        val foodRecipesList = repository.getRecipesDB()
        emit(Resource.Success(foodRecipesList))
    }.catch { throwable ->

        emit(Resource.Error(throwable = throwable))
    }
}
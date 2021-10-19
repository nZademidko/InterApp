package com.inter.courseapp.models.usecases

import com.inter.courseapp.models.entities.database.FoodRecipeEntity
import com.inter.courseapp.models.entities.foodRecipe.FoodRecipe
import com.inter.courseapp.models.entities.state.Resource
import kotlinx.coroutines.flow.Flow

interface GetFavoritesRecipesUseCase {

    operator fun invoke(): Flow<Resource<List<FoodRecipeEntity>>>
}
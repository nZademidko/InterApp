package com.inter.courseapp.models.usecases

import com.inter.courseapp.models.entities.foodRecipe.FoodRecipe
import com.inter.courseapp.models.entities.state.Resource
import kotlinx.coroutines.flow.Flow

interface GetFoodRecipesUseCase {

    operator fun invoke(queries: Map<String, String>): Flow<Resource<List<FoodRecipe>>>

}
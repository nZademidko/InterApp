package com.inter.courseapp.models.usecases

import com.example.foody.models.FoodRecipe
import com.inter.courseapp.models.entities.state.LoadingState
import kotlinx.coroutines.flow.Flow

interface GetFoodRecipesUseCase {

    operator fun invoke(queries: Map<String, String>): Flow<LoadingState<List<FoodRecipe>>>

}
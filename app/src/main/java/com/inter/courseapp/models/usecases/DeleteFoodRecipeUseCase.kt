package com.inter.courseapp.models.usecases

import com.inter.courseapp.models.entities.database.FoodRecipeEntity
import com.inter.courseapp.models.entities.state.Resource
import kotlinx.coroutines.flow.Flow

interface DeleteFoodRecipeUseCase {

    operator fun invoke(foodRecipeEntity: FoodRecipeEntity): Flow<Resource<String>>

}
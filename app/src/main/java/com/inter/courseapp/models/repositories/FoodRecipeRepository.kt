package com.inter.courseapp.models.repositories

import com.inter.courseapp.data.database.FoodRecipesDao
import com.inter.courseapp.data.network.FoodService
import com.inter.courseapp.models.entities.database.FoodRecipeEntity
import com.inter.courseapp.models.entities.foodRecipe.FoodRecipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FoodRecipeRepository @Inject constructor(
    private val remoteApi: FoodService,
    private val foodRecipesDao: FoodRecipesDao
) {

    suspend fun getRecipes(queries: Map<String, String>) =
        withContext(Dispatchers.IO) {
            remoteApi.getRecipes(queries = queries).results
        }

    suspend fun saveFoodRecipe(foodRecipe: FoodRecipe) =
        withContext(Dispatchers.IO) {
            foodRecipesDao.loadFoodRecipe(
                foodRecipeEntity = FoodRecipeEntity(
                    id = 0,
                    foodRecipe = foodRecipe
                )
            )
        }
}
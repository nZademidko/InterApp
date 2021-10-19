package com.inter.courseapp.models.repositories

import com.inter.courseapp.data.database.FoodRecipesDao
import com.inter.courseapp.models.entities.database.FoodRecipeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoritesRecipesRepository @Inject constructor(
    private val foodRecipesDatabase: FoodRecipesDao
) {

    suspend fun getRecipesDB() =
        withContext(Dispatchers.IO) {
            foodRecipesDatabase.getAllRecipes()
        }

    suspend fun deleteRecipe(foodRecipeEntity: FoodRecipeEntity) =
        withContext(Dispatchers.IO) {
            foodRecipesDatabase.deleteFoodRecipe(foodRecipeEntity = foodRecipeEntity)
        }

}
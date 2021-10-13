package com.inter.courseapp.models.repositories

import com.inter.courseapp.source.api.FoodService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FoodRecipeRepository @Inject constructor(
    private val api: FoodService
) {

    suspend fun getRecipes(queries: Map<String, String>) =
        withContext(Dispatchers.IO) {
            api.getRecipes(queries).results
        }
}
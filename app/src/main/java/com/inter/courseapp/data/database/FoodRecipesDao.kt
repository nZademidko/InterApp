package com.inter.courseapp.data.database

import androidx.room.*
import com.inter.courseapp.FAVORITES_FOOD_RECIPES_TABLE
import com.inter.courseapp.models.entities.database.FoodRecipeEntity
import com.inter.courseapp.models.entities.foodRecipe.FoodRecipe

@Dao
interface FoodRecipesDao {

    @Insert(entity = FoodRecipeEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun loadFoodRecipe(foodRecipeEntity: FoodRecipeEntity)

    @Query("SELECT * FROM food_recipes_table ORDER BY id ASC")
    suspend fun getAllRecipes(): List<FoodRecipeEntity>

    @Delete(entity = FoodRecipeEntity::class)
    suspend fun deleteFoodRecipe(foodRecipeEntity: FoodRecipeEntity)

}
package com.inter.courseapp.models.entities.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.inter.courseapp.FAVORITES_FOOD_RECIPES_TABLE
import com.inter.courseapp.models.entities.foodRecipe.FoodRecipe

@Entity(tableName = FAVORITES_FOOD_RECIPES_TABLE)
data class FoodRecipeEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var foodRecipe: FoodRecipe
)
package com.inter.courseapp.models.entities.api

import com.inter.courseapp.models.entities.foodRecipe.FoodRecipe
import com.google.gson.annotations.SerializedName

data class FoodRecipeResponse(
    @SerializedName("results")
    val results: List<FoodRecipe>
)
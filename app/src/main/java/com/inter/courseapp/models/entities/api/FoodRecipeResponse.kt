package com.inter.courseapp.models.entities.api

import com.example.foody.models.FoodRecipe
import com.google.gson.annotations.SerializedName

data class FoodRecipeResponse(
    @SerializedName("results")
    val results: List<FoodRecipe>
)
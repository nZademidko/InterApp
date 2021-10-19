package com.inter.courseapp.data.network

import com.inter.courseapp.models.entities.api.FoodRecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface FoodService{

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries: Map<String, String>
    ): FoodRecipeResponse
}

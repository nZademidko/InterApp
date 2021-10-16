package com.inter.courseapp.models.usecases.impl

import com.inter.courseapp.models.entities.foodRecipe.FoodRecipe
import com.inter.courseapp.models.entities.state.Resource
import com.inter.courseapp.models.repositories.FoodRecipeRepository
import com.inter.courseapp.models.usecases.GetFoodRecipesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFoodRecipesUseCaseImpl @Inject constructor(
    private val repository: FoodRecipeRepository
) : GetFoodRecipesUseCase {

    override fun invoke(queries: Map<String, String>): Flow<Resource<List<FoodRecipe>>> {
        return flow {

            emit(Resource.Loading)

            val foodRecipesList = repository.getRecipes(queries = queries)
            emit(Resource.Success(foodRecipesList))

        }.catch { throwable ->

            emit(Resource.Error(throwable = throwable))

        }
    }

}
package com.inter.courseapp.models.usecases.impl

import com.example.foody.models.FoodRecipe
import com.inter.courseapp.models.entities.state.LoadingState
import com.inter.courseapp.models.repositories.FoodRecipeRepository
import com.inter.courseapp.models.usecases.GetFoodRecipesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFoodRecipesUseCaseImpl @Inject constructor(
    private val repository: FoodRecipeRepository
) : GetFoodRecipesUseCase {

    override fun invoke(queries: Map<String, String>): Flow<LoadingState<List<FoodRecipe>>> {
        return flow {

            emit(LoadingState.Loading)

            val foodRecipesList = repository.getRecipes(queries = queries)
            emit(LoadingState.Success(foodRecipesList))

        }.catch { throwable ->

            emit(LoadingState.Error(throwable = throwable))

        }
    }

}
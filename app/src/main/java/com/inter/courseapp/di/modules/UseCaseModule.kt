package com.inter.courseapp.di.modules

import com.inter.courseapp.models.usecases.DeleteFoodRecipeUseCase
import com.inter.courseapp.models.usecases.GetFavoritesRecipesUseCase
import com.inter.courseapp.models.usecases.GetFoodRecipesUseCase
import com.inter.courseapp.models.usecases.SaveFoodRecipeUseCase
import com.inter.courseapp.models.usecases.impl.DeleteFoodRecipeUseCaseImpl
import com.inter.courseapp.models.usecases.impl.GetFavoritesRecipesUseCaseImpl
import com.inter.courseapp.models.usecases.impl.GetFoodRecipesUseCaseImpl
import com.inter.courseapp.models.usecases.impl.SaveFoodRecipeUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

    @Binds
    fun bindGetFoodRecipesModule(useCase: GetFoodRecipesUseCaseImpl):
            GetFoodRecipesUseCase

    @Binds
    fun bindGetFavoritesRecipesModule(useCase: GetFavoritesRecipesUseCaseImpl):
            GetFavoritesRecipesUseCase

    @Binds
    fun bindSaveFoodRecipeModule(useCase: SaveFoodRecipeUseCaseImpl):
            SaveFoodRecipeUseCase

    @Binds
    fun bindDeleteFoodRecipeModule(useCase: DeleteFoodRecipeUseCaseImpl):
            DeleteFoodRecipeUseCase
}
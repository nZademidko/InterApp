package com.inter.courseapp.di.modules

import com.inter.courseapp.models.usecases.GetFoodRecipesUseCase
import com.inter.courseapp.models.usecases.impl.GetFoodRecipesUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

    @Binds
    fun bindGetFoodRecipesModule(useCase: GetFoodRecipesUseCaseImpl): GetFoodRecipesUseCase

}
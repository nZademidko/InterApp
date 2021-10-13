package com.inter.courseapp.di.modules

import com.inter.courseapp.source.api.FoodService
import com.inter.courseapp.utils.FoodServiceBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideAnimeService(): FoodService = FoodServiceBuilder().build()
}
package com.inter.courseapp.di.modules

import android.content.Context
import androidx.room.Room
import com.inter.courseapp.FOOD_RECIPES_DATABASE_NAME
import com.inter.courseapp.MainApplication
import com.inter.courseapp.data.database.FoodRecipesDao
import com.inter.courseapp.data.database.FoodRecipesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideFoodRecipesDao(context: Context): FoodRecipesDao =
        FoodRecipesDatabase.getInstance(context).foodRecipesDao()
}

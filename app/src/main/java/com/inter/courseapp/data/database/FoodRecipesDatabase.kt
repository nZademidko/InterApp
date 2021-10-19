package com.inter.courseapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.inter.courseapp.FOOD_RECIPES_DATABASE_NAME
import com.inter.courseapp.models.entities.database.FoodRecipeEntity
import com.inter.courseapp.utils.FoodRecipesTypeConverter


@Database(
    entities = [FoodRecipeEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(FoodRecipesTypeConverter::class)
abstract class FoodRecipesDatabase : RoomDatabase() {

    abstract fun foodRecipesDao(): FoodRecipesDao

    companion object {
        private var INSTANCE: FoodRecipesDatabase? = null

        fun getInstance(context: Context): FoodRecipesDatabase {
            if (INSTANCE == null) {
                synchronized(FoodRecipesDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FoodRecipesDatabase::class.java,
                        FOOD_RECIPES_DATABASE_NAME
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}
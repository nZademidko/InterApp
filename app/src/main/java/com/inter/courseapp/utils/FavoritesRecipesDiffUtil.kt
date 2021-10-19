package com.inter.courseapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.inter.courseapp.models.entities.database.FoodRecipeEntity
import com.inter.courseapp.models.entities.foodRecipe.FoodRecipe

class FavoritesRecipesDiffUtil(
    private val oldList: List<FoodRecipeEntity>,
    private val newList: List<FoodRecipeEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize() =
        oldList.size

    override fun getNewListSize() =
        newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

}
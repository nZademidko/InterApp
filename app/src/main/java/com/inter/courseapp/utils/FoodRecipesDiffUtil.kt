package com.inter.courseapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.inter.courseapp.models.entities.foodRecipe.FoodRecipe

class FoodRecipesDiffUtil(
    private val oldList: List<FoodRecipe>,
    private val newList: List<FoodRecipe>
) : DiffUtil.Callback() {

    override fun getOldListSize() =
        oldList.size

    override fun getNewListSize() =
        newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

}
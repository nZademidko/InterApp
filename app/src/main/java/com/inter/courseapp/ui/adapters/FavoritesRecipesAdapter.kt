package com.inter.courseapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.inter.courseapp.databinding.FoodRecipeItemListBinding
import com.inter.courseapp.models.entities.database.FoodRecipeEntity
import com.inter.courseapp.models.entities.foodRecipe.FoodRecipe
import com.inter.courseapp.utils.FavoritesRecipesDiffUtil
import com.inter.courseapp.utils.FoodRecipesDiffUtil
import com.inter.courseapp.utils.fromHtmlToText

class FavoritesRecipesAdapter(
    private val onItemClicked: (FoodRecipe) -> Unit
) : RecyclerView.Adapter<FavoritesRecipesAdapter.FoodRecipesViewHolder>() {

    private var foodRecipesList = mutableListOf<FoodRecipeEntity>()

    inner class FoodRecipesViewHolder(private val binding: FoodRecipeItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(foodRecipe: FoodRecipe) {

            with(binding) {

                ivFood.load(foodRecipe.image) {
                    crossfade(true)
                    crossfade(500)
                    transformations(CircleCropTransformation())
                }
                tvName.text = foodRecipe.title
                tvDescription.text = fromHtmlToText(text = foodRecipe.summary)
                tvLikes.text = foodRecipe.aggregateLikes.toString()
                tvCookingTime.text = foodRecipe.readyInMinutes.toString()

                cdRow.setOnClickListener {
                    onItemClicked(foodRecipe)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodRecipesViewHolder =
        FoodRecipesViewHolder(
            FoodRecipeItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: FoodRecipesViewHolder, position: Int) {
        holder.bind(foodRecipe = foodRecipesList[position].foodRecipe)
    }

    override fun getItemCount() = foodRecipesList.size

    fun reloadData() {
        setData(newData = foodRecipesList)
    }

    fun setData(newData: MutableList<FoodRecipeEntity>) {

        val recipesDiffUtil = FavoritesRecipesDiffUtil(oldList = foodRecipesList, newList = newData)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        foodRecipesList.clear()
        foodRecipesList.addAll(newData)
        diffUtilResult.dispatchUpdatesTo(this)
    }

    fun getFoodRecipeList() = foodRecipesList

    fun getCurrentElement(position: Int) =
        foodRecipesList[position]

}
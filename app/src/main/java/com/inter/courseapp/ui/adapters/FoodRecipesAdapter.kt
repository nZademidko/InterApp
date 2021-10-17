package com.inter.courseapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.inter.courseapp.databinding.FoodRecipeItemListBinding
import com.inter.courseapp.models.entities.foodRecipe.FoodRecipe
import com.inter.courseapp.utils.FoodRecipesDiffUtil
import com.inter.courseapp.utils.fromHtmlToText

class FoodRecipesAdapter(
    private val onItemClicked: (FoodRecipe) -> Unit
) : RecyclerView.Adapter<FoodRecipesAdapter.FoodRecipesViewHolder>() {

    private var foodRecipesList = listOf<FoodRecipe>()

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
        holder.bind(foodRecipe = foodRecipesList[position])
    }

    override fun getItemCount() = foodRecipesList.size

    fun revertItem(position: Int) {
        notifyItemChanged(position)
    }

    fun setData(newData: List<FoodRecipe>) {

        val recipesDiffUtil = FoodRecipesDiffUtil(oldList = foodRecipesList, newList = newData)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)

        foodRecipesList = newData
        diffUtilResult.dispatchUpdatesTo(this)

    }

}
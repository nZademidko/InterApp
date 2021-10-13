package com.inter.courseapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.inter.courseapp.R
import com.inter.courseapp.databinding.FragmentFoodRecipesBinding
import com.inter.courseapp.extensions.launchWhenStarted
import com.inter.courseapp.ui.architecture.BaseFragment
import com.inter.courseapp.ui.viewmodels.FoodRecipesViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FoodRecipesFragment @Inject constructor(
) : BaseFragment<FragmentFoodRecipesBinding, FoodRecipesViewModel>(
    R.layout.fragment_food_recipes
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    private fun setupObservers() {


        viewModel.loadRecipes(viewModel.applyQueries()).onEach {



        }.launchWhenStarted(lifecycleScope)


    }

    override fun getViewBinding(layoutInflater: LayoutInflater) =
        FragmentFoodRecipesBinding.inflate(layoutInflater)

}
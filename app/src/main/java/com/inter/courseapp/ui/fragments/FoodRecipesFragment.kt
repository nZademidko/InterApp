package com.inter.courseapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.inter.courseapp.models.entities.foodRecipe.FoodRecipe
import com.inter.courseapp.R
import com.inter.courseapp.databinding.FragmentFoodRecipesBinding
import com.inter.courseapp.di.utils.ViewModelFactory
import com.inter.courseapp.extensions.launchWhenStarted
import com.inter.courseapp.models.entities.state.FoodListState
import com.inter.courseapp.ui.adapters.FoodRecipesAdapter
import com.inter.courseapp.ui.architecture.BaseFragment
import com.inter.courseapp.ui.viewmodels.FoodRecipesViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FoodRecipesFragment @Inject constructor(
    viewModelFactory: ViewModelFactory
) :
    BaseFragment<FragmentFoodRecipesBinding>(
        R.layout.fragment_food_recipes
    ) {

    private val viewModel: FoodRecipesViewModel by viewModels { viewModelFactory }
    private val foodRecipeAdapter by lazy { FoodRecipesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupUI()
        setupList()
    }

    private fun setupList() {
        viewModel.loadRecipes(viewModel.applyQueries())
    }

    private fun setupUI() =
        with(binding) {

            with(rvFoodRecipes) {
                adapter = foodRecipeAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }

        }

    private fun setupObservers() {
        viewModel.listRecipesState.onEach { loadingResult ->
            when (loadingResult) {
                is FoodListState.Loading -> onFoodStateListLoading()
                is FoodListState.Success -> onFoodStateListSuccess(loadingResult.data)
                is FoodListState.Error -> onFoodStateListError(loadingResult.message)
            }
        }.launchWhenStarted(lifecycleScope)
    }

    private fun onFoodStateListLoading() {
        sendSnackBarMessage("Загрузка данных...")
    }

    private fun onFoodStateListSuccess(data: List<FoodRecipe>) {

        foodRecipeAdapter.setData(data)

    }

    private fun onFoodStateListError(message: String) {
        sendSnackBarMessage(message = message)
    }

    override fun getViewBinding(layoutInflater: LayoutInflater) =
        FragmentFoodRecipesBinding.inflate(layoutInflater)

}
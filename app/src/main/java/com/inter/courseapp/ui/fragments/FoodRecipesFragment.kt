package com.inter.courseapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.inter.courseapp.models.entities.foodRecipe.FoodRecipe
import com.inter.courseapp.R
import com.inter.courseapp.databinding.FragmentFoodRecipesBinding
import com.inter.courseapp.di.utils.ViewModelFactory
import com.inter.courseapp.extensions.launchWhenStarted
import com.inter.courseapp.models.entities.state.FoodListState
import com.inter.courseapp.ui.adapters.FoodRecipesAdapter
import com.inter.courseapp.ui.adapters.decorations.FoodRecipesVerticalDividerItemDecoration
import com.inter.courseapp.ui.adapters.touchhelpers.SwipeToSave
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

    private val foodRecipeAdapter by lazy { FoodRecipesAdapter(onItemClicked = onItemClicked) }
    private val onItemClicked = { item: FoodRecipe ->
        val action = FoodRecipesFragmentDirections.foodRecipesToDetails(item)
        findNavController().navigate(action)
    }

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
                addItemDecoration(
                    FoodRecipesVerticalDividerItemDecoration(
                        innerDivider = 16,
                        outerDivider = 8
                    )
                )
                adapter = foodRecipeAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
            initSwipeToSave()
        }

    private fun initSwipeToSave() {
        val onItemSwipeToSave = { position: Int ->
            foodRecipeAdapter.revertItem(position)
            sendSnackBarMessage("$position Сохранен")
        }
        val swipeToSaveCallback = SwipeToSave(onItemSave = onItemSwipeToSave)
        ItemTouchHelper(swipeToSaveCallback).attachToRecyclerView(binding.rvFoodRecipes)
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
        showShimmerEffect()
    }

    private fun onFoodStateListSuccess(data: List<FoodRecipe>) {
        hideShimmerEffect()
        foodRecipeAdapter.setData(data)
    }

    private fun onFoodStateListError(message: String) {
        hideShimmerEffect()
        sendSnackBarMessage(message = message)
    }

    private fun showShimmerEffect() {
        binding.rvFoodRecipes.showShimmer()
    }

    private fun hideShimmerEffect() {
        binding.rvFoodRecipes.hideShimmer()
    }

    override fun getViewBinding(layoutInflater: LayoutInflater) =
        FragmentFoodRecipesBinding.inflate(layoutInflater)

}
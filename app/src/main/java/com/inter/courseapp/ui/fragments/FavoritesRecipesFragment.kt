package com.inter.courseapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.inter.courseapp.R
import com.inter.courseapp.databinding.FragmentFavoritesRecipesBinding
import com.inter.courseapp.di.utils.ViewModelFactory
import com.inter.courseapp.extensions.launchWhenStarted
import com.inter.courseapp.models.entities.database.FoodRecipeEntity
import com.inter.courseapp.models.entities.foodRecipe.FoodRecipe
import com.inter.courseapp.models.entities.state.FoodListState
import com.inter.courseapp.models.entities.state.Resource
import com.inter.courseapp.ui.adapters.FavoritesRecipesAdapter
import com.inter.courseapp.ui.adapters.decorations.FoodRecipesVerticalDividerItemDecoration
import com.inter.courseapp.ui.adapters.touchhelpers.SwipeToDeleteTouchHelper
import com.inter.courseapp.ui.architecture.BaseFragment
import com.inter.courseapp.ui.viewmodels.FavoritesRecipesViewModel
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

class FavoritesRecipesFragment @Inject constructor(
    viewModelFactory: ViewModelFactory
) : BaseFragment<FragmentFavoritesRecipesBinding>(
    R.layout.fragment_favorites_recipes
) {

    private var foodRecipesList = mutableListOf<FoodRecipeEntity>()

    private val viewModel: FavoritesRecipesViewModel by viewModels { viewModelFactory }
    private val foodRecipeAdapter by lazy { FavoritesRecipesAdapter(onItemClicked = onItemClicked) }
    private val onItemClicked = { item: FoodRecipe ->

        val action = FavoritesRecipesFragmentDirections.favoritesRecipesToDetails(item)
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupUI()
        setupList()
    }

    private fun setupList() {
        viewModel.loadRecipes()
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
            initSwipeToDelete()
        }

    private fun initSwipeToDelete() {
        val onItemSwipeToDelete = { position: Int ->
            Timber.v("foodRecipesList = ${foodRecipesList.size}\nfoodRecipesListAdapter = ${foodRecipeAdapter.getFoodRecipeList().size}\n")
            foodRecipesList.removeAt(position)
            viewModel.deleteRecipe(foodRecipeAdapter.getCurrentElement(position = position))
        }
        val swipeToDeleteCallback = SwipeToDeleteTouchHelper(onItemDelete = onItemSwipeToDelete)
        ItemTouchHelper(swipeToDeleteCallback).attachToRecyclerView(binding.rvFoodRecipes)
    }

    private fun setupObservers() {
        viewModel.listRecipesState.onEach { loadingResult ->
            when (loadingResult) {
                is FoodListState.Loading -> onFoodStateListLoading()
                is FoodListState.Success -> onFoodStateListSuccess(loadingResult.data)
                is FoodListState.Error -> onFoodStateListError(loadingResult.message)
            }

        }.launchWhenStarted(lifecycleScope = lifecycleScope)

        viewModel.deleteRecipeState.onEach { loadingResult ->
            when (loadingResult) {

                is Resource.Loading -> Unit
                is Resource.Error ->
                    onDeleteRecipeStateError(throwable = loadingResult.throwable)
                is Resource.Success ->
                    onDeleteRecipeStateSuccess()
            }

        }.launchWhenStarted(lifecycleScope = lifecycleScope)
    }

    private fun onDeleteRecipeStateError(throwable: Throwable) {
        sendSnackBarMessage("Ошибка: ${throwable.message}")
        foodRecipesList = foodRecipeAdapter.getFoodRecipeList()
        foodRecipeAdapter.reloadData()
    }

    private fun onDeleteRecipeStateSuccess() {
        sendSnackBarMessage("Рецепт удален.")
        foodRecipeAdapter.setData(newData = foodRecipesList)
    }

    private fun onFoodStateListLoading() {
        showShimmerEffect()
    }

    private fun onFoodStateListSuccess(data: List<FoodRecipeEntity>) {
        hideShimmerEffect()
        foodRecipesList = data.toMutableList()
        foodRecipeAdapter.setData(data.toMutableList())
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
        FragmentFavoritesRecipesBinding.inflate(layoutInflater)

}
package com.inter.courseapp.di.modules

import androidx.lifecycle.ViewModel
import com.inter.courseapp.di.utils.ViewModelKey
import com.inter.courseapp.ui.viewmodels.FavoritesRecipesViewModel
import com.inter.courseapp.ui.viewmodels.FoodRecipesViewModel
import com.inter.courseapp.ui.viewmodels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FoodRecipesViewModel::class)
    fun bindFoodRecipesViewModel(viewModel: FoodRecipesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesRecipesViewModel::class)
    fun bindFavoritesRecipesViewModel(viewModel: FavoritesRecipesViewModel): ViewModel

}
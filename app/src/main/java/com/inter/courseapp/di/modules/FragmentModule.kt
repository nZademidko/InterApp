package com.inter.courseapp.di.modules

import androidx.fragment.app.Fragment
import com.inter.courseapp.di.utils.FragmentKey
import com.inter.courseapp.ui.fragments.DetailsFragment
import com.inter.courseapp.ui.fragments.FavoritesRecipesFragment
import com.inter.courseapp.ui.fragments.FoodRecipesFragment
import com.inter.courseapp.ui.fragments.MainFragment

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(MainFragment::class)
    fun bindMainFragment(fragment: MainFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(FoodRecipesFragment::class)
    fun bindFoodRecipesFragment(fragment: FoodRecipesFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(FavoritesRecipesFragment::class)
    fun bindFavoritesRecipesFragment(fragment: FavoritesRecipesFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(DetailsFragment::class)
    fun bindDetailsFragment(fragment: DetailsFragment): Fragment
}
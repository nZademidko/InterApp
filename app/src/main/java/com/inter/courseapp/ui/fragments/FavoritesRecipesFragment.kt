package com.inter.courseapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.inter.courseapp.R
import com.inter.courseapp.databinding.FragmentFavoritesRecipesBinding
import com.inter.courseapp.ui.architecture.BaseFragment
import javax.inject.Inject

class FavoritesRecipesFragment @Inject constructor() :
    BaseFragment<FragmentFavoritesRecipesBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getViewBinding(layoutInflater: LayoutInflater) =
        FragmentFavoritesRecipesBinding.inflate(layoutInflater)

}
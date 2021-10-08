package com.inter.courseapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.inter.courseapp.R
import com.inter.courseapp.databinding.FragmentFoodRecipesBinding
import com.inter.courseapp.ui.architecture.BaseFragment
import javax.inject.Inject

class FoodRecipesFragment @Inject constructor() :
    BaseFragment<FragmentFoodRecipesBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("GGG", "STARTED")
    }

    override fun getViewBinding(layoutInflater: LayoutInflater) =
        FragmentFoodRecipesBinding.inflate(layoutInflater)

}
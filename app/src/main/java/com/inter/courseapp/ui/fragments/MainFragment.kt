package com.inter.courseapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.inter.courseapp.R
import com.inter.courseapp.databinding.FragmentMainBinding
import com.inter.courseapp.extensions.setupWithNavController
import com.inter.courseapp.ui.architecture.BaseFragment
import javax.inject.Inject

class MainFragment @Inject constructor() : BaseFragment<FragmentMainBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBottomNavigation()

    }

    private fun setupBottomNavigation() = binding
        .bottomNavigationView.apply {
            setupWithNavController(
                navGraphIds = listOf(
                    R.navigation.recipes_nav,
                    R.navigation.favorite_recipes_nav
                ),
                fragmentManager = childFragmentManager,
                containerId = R.id.mainHostFragment,
                intent = requireActivity().intent
            )
        }

    override fun getViewBinding(layoutInflater: LayoutInflater) =
        FragmentMainBinding.inflate(layoutInflater)


}
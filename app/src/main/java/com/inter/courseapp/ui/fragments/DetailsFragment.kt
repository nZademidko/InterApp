package com.inter.courseapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.inter.courseapp.R
import com.inter.courseapp.databinding.FragmentDetailsBinding
import com.inter.courseapp.di.utils.ViewModelFactory
import com.inter.courseapp.ui.architecture.BaseFragment
import com.inter.courseapp.ui.viewmodels.DetailsViewModel
import com.inter.courseapp.utils.fromHtmlToText
import javax.inject.Inject


class DetailsFragment @Inject constructor(
    viewModelFactory: ViewModelFactory
) : BaseFragment<FragmentDetailsBinding>(R.layout.fragment_details) {

    private val viewModel: DetailsViewModel by viewModels { viewModelFactory }
    private val args by navArgs<DetailsFragmentArgs>()
    private val foodRecipe by lazy { args.foodRecipe }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = with(binding) {

        ivFood.load(args.foodRecipe.image)

        tvName.text = foodRecipe.title
        tvDescription.text = fromHtmlToText(text = foodRecipe.summary)

        ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        if (foodRecipe.vegetarian) {
            ivVegetarian.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green_100))
            tvVegetarian.setTextColor(ContextCompat.getColor(requireContext(),R.color.green_100))
        }

        if (foodRecipe.vegan) {
            ivVegan.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green_100))
            tvVegan.setTextColor(ContextCompat.getColor(requireContext(),R.color.green_100))
        }

        if (foodRecipe.glutenFree) {
            ivGlutenFree.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green_100))
            tvGlutenFree.setTextColor(ContextCompat.getColor(requireContext(),R.color.green_100))
        }

        if (foodRecipe.dairyFree) {
            ivDairyFree.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green_100))
            tvDairyFree.setTextColor(ContextCompat.getColor(requireContext(),R.color.green_100))
        }

        if (foodRecipe.veryHealthy) {
            ivHealthy.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green_100))
            tvHealthy.setTextColor(ContextCompat.getColor(requireContext(),R.color.green_100))
        }

        if (foodRecipe.cheap) {
            ivCheap.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green_100))
            tvCheap.setTextColor(ContextCompat.getColor(requireContext(),R.color.green_100))
        }
    }

    override fun getViewBinding(layoutInflater: LayoutInflater) =
        FragmentDetailsBinding.inflate(layoutInflater)
}
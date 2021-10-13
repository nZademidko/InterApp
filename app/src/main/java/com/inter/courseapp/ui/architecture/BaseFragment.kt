package com.inter.courseapp.ui.architecture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.inter.courseapp.di.utils.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding, out VM>(
    layoutId: Int,
) : Fragment(layoutId) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    protected val viewModel: VM by viewModels { viewModelFactory }

    private var _binding: VB? = null
    protected val binding get() = _binding!!
    abstract fun getViewBinding(layoutInflater: LayoutInflater): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
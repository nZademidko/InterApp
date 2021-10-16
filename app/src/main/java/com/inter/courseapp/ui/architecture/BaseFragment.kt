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
import com.google.android.material.snackbar.Snackbar
import com.inter.courseapp.di.utils.ViewModelFactory
import com.inter.courseapp.ui.viewmodels.MainViewModel
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding>(
    layoutId: Int,
) : Fragment(layoutId) {



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


    fun sendSnackBarMessage(message: String) =
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
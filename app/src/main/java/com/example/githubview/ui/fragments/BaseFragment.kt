package com.example.githubview.ui.fragments

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.githubview.viewmodels.MainActivityViewModel
import com.resocoder.mvvmbasicstut.utilities.InjectorUtils

open class BaseFragment: Fragment() {
    private val factory by lazy { InjectorUtils.provideViewModelFactory(context!!) }
    protected val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(MainActivityViewModel::class.java)
    }
}
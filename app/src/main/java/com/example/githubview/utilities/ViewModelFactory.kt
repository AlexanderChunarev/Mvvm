package com.example.githubview.utilities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubview.reposetories.UserRepository
import com.example.githubview.viewmodels.MainActivityViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(
            userRepository
        ) as T
    }
}
package com.resocoder.mvvmbasicstut.utilities

import android.content.Context
import com.example.githubview.databases.RepoDataBase
import com.example.githubview.reposetories.UserRepository
import com.example.githubview.utilities.ViewModelFactory

object InjectorUtils {

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val quoteRepository = UserRepository.getInstance(RepoDataBase.getInstance(context))
        return ViewModelFactory(quoteRepository)
    }
}
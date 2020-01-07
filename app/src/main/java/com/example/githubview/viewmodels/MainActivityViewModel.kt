package com.example.githubview.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubview.reposetories.UserRepository
import com.example.githubview.responces.RepoResponse
import com.example.githubview.responces.UserResponse
import com.example.githubview.utilities.MessageUtils
import com.example.githubview.utilities.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainActivityViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    val message: SingleLiveEvent<MessageUtils> = SingleLiveEvent()
    val userLiveData = MutableLiveData<List<UserResponse>>()
    val repoLiveData = MutableLiveData<List<RepoResponse>>()


    fun addUserData(login: String) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.apply {
            try {
                addUserInfo(login)
                getUsersData()
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    message.value = MessageUtils.ErrorMessage("User not found")
                }
            }
        }
    }

    fun getUsersData() = viewModelScope.launch(Dispatchers.Main) {
        userLiveData.value = userRepository.fetchUsers()
    }

    fun getUserReposData(login: String) = viewModelScope.launch(Dispatchers.Main) {
        repoLiveData.value = userRepository.fetchUserRepo(login)
    }

    fun deleteUserData(login: String) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.apply {
            deleteUser(login)
            getUsersData()
        }
    }
}
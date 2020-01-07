package com.example.githubview.reposetories

import com.example.githubview.builder.ServiceBuilder
import com.example.githubview.databases.RepoDataBase
import com.example.githubview.extentions.await
import com.example.githubview.extentions.insertAllTo
import com.example.githubview.extentions.insertTo
import com.example.githubview.responces.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(
    private val repoDataBase: RepoDataBase
) {
    private val serviceBuilder by lazy { ServiceBuilder() }

    suspend fun addUserInfo(login: String) = withContext(Dispatchers.IO) {
        serviceBuilder.gitHubService().apply {
            getUserInfo(login).await().insertTo(repoDataBase)
            getRepoInfo(login).await().insertAllTo(repoDataBase)
        }
    }

    suspend fun fetchUsers(): List<UserResponse> = withContext(Dispatchers.IO) {
        repoDataBase.userDao().getAll()
    }

    suspend fun fetchUserRepo(login: String) = withContext(Dispatchers.IO) {
        repoDataBase.repoDao().getAllReposByLogin(login)
    }

    suspend fun deleteUser(login: String) = withContext(Dispatchers.IO) {
        repoDataBase.userDao().delete(login)
        repoDataBase.repoDao().delete(login)
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(repoDb: RepoDataBase) =
            instance ?: synchronized(this) {
                instance
                    ?: UserRepository(repoDb).also { instance = it }
            }
    }

}

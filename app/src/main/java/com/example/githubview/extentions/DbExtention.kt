package com.example.githubview.extentions

import com.example.githubview.databases.RepoDataBase
import com.example.githubview.responces.RepoResponse
import com.example.githubview.responces.UserResponse

fun List<RepoResponse>.insertAllTo(repoDataBase: RepoDataBase) {
    repoDataBase.repoDao().insertAll(this)
}

fun UserResponse.insertTo(repoDataBase: RepoDataBase) {
    repoDataBase.userDao().insert(this)
}
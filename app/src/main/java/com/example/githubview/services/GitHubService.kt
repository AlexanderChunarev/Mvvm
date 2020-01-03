package com.example.githubview.services

import com.example.githubview.responces.RepoResponse
import com.example.githubview.responces.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{login}")
    fun getUserInfo(@Path("login") login: String): Call<UserResponse>

    @GET("users/{login}/repos")
    fun getRepoInfo(@Path("login") login: String): Call<List<RepoResponse>>
}
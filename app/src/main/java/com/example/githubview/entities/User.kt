package com.example.githubview.entities

data class User(
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val followers: Int,
    val following: Int
)
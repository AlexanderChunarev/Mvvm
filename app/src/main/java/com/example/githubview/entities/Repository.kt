package com.example.githubview.entities

data class Repository(
    val id: Int,
    val userId: Int,
    val name: String,
    val language: String,
    val updatedDate: String
)
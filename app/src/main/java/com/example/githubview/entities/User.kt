package com.example.githubview.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "login")val login: String,
    @ColumnInfo(name = "avatar_url")val avatarUrl: String,
    @ColumnInfo(name = "followers")val followers: Int,
    @ColumnInfo(name = "following")val following: Int
)
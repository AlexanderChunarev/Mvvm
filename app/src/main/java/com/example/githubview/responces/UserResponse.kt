package com.example.githubview.responces

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
class UserResponse(
    @PrimaryKey
    @Json(name = "id")
    val id: Int,

    @ColumnInfo
    @Json(name = "login")
    val login: String,

    @ColumnInfo
    @Json(name = "avatar_url")
    val avatar_url: String,

    @ColumnInfo
    @Json(name = "followers")
    val followers: Int,

    @ColumnInfo
    @Json(name = "following")
    val following: Int
)
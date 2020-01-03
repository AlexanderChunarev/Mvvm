package com.example.githubview.responces

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UserResponse(
    @Json(name = "login") val login: String,
    @Json(name = "avatar_url") val avatar_url: String,
    @Json(name = "followers") val followers: Int,
    @Json(name = "following") val following: Int
)
package com.example.githubview.responces

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class RepoResponse(
    @Json(name = "id") val repoId: String,
    @Json(name = "name") val name: String,
    @Json(name = "language") val language: String?,
    @Json(name = "updated_at") val updated_at: String
)
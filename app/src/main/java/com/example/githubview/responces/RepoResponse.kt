package com.example.githubview.responces

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.githubview.entities.Owner
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@Entity
@JsonClass(generateAdapter = true)
class RepoResponse(
    @PrimaryKey
    @Json(name = "id")
    val repoId: Int,

    @ColumnInfo
    @Json(name = "owner")
    val ownerLogin: Owner,

    @ColumnInfo
    @Json(name = "name")
    val name: String,

    @ColumnInfo
    @Json(name = "language")
    val language: String?,

    @ColumnInfo
    @Json(name = "updated_at")
    val updated_at: String
)
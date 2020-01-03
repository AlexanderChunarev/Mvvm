package com.example.githubview.entities

import androidx.room.Embedded
import androidx.room.Relation

data class UserRepos(
    @Embedded val repository: Repository,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val userRepos: List<Repository>
)

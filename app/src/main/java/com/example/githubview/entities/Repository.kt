package com.example.githubview.entities

import androidx.room.*

@Entity
data class Repository(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "userId") val userId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "language") val language: String,
    @ColumnInfo(name = "updatedDate") val updatedDate: String
)
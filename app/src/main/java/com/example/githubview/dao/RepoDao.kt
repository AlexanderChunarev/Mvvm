package com.example.githubview.dao

import androidx.room.*
import com.example.githubview.entities.Repository
import com.example.githubview.entities.UserRepos

@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: Repository)

    @Delete
    fun delete(repository: Repository)

    @Query("SELECT * FROM repository WHERE userId = :userId")
    fun getAll(userId: Int): List<Repository>

    @Transaction
    @Query("SELECT * FROM repository WHERE userId = :userId")
    fun getUsersWithPlaylists(userId: Int): List<UserRepos>
}
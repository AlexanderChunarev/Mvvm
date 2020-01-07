package com.example.githubview.dao

import androidx.room.*
import com.example.githubview.responces.RepoResponse

@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(repos: List<RepoResponse>)

    @Query("DELETE FROM RepoResponse WHERE ownerLogin = :login")
    fun delete(login: String)

    @Transaction
    @Query("SELECT * FROM RepoResponse WHERE ownerLogin = :login")
    fun getAllReposByLogin(login: String): List<RepoResponse>
}
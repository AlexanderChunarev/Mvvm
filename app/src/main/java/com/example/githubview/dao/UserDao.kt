package com.example.githubview.dao

import androidx.room.*
import com.example.githubview.responces.UserResponse

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserResponse)

    @Query("DELETE FROM UserResponse WHERE login = :login")
    fun delete(login: String)

    @Query("SELECT * FROM UserResponse")
    fun getAll(): List<UserResponse>
}
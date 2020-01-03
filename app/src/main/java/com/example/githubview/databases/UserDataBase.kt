package com.example.githubview.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubview.dao.UserDao
import com.example.githubview.entities.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UserDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {

        private const val DATABASE = "users"

        @Volatile
        private var instance: UserDataBase? = null

        fun getInstance(context: Context): UserDataBase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): UserDataBase {
            return Room.databaseBuilder(context, UserDataBase::class.java, DATABASE)
                .build()
        }
    }
}
package com.example.githubview.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.githubview.converters.OwnerConverter
import com.example.githubview.dao.RepoDao
import com.example.githubview.dao.UserDao
import com.example.githubview.entities.Repository
import com.example.githubview.entities.User
import com.example.githubview.responces.RepoResponse
import com.example.githubview.responces.UserResponse

@Database(
    entities = [RepoResponse::class, UserResponse::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(OwnerConverter::class)
abstract class RepoDataBase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
    abstract fun userDao(): UserDao

    companion object {
        private const val DATABASE = "repos"

        @Volatile
        private var instance: RepoDataBase? = null

        fun getInstance(context: Context): RepoDataBase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): RepoDataBase {
            return Room.databaseBuilder(context, RepoDataBase::class.java, DATABASE)
                .build()
        }
    }
}
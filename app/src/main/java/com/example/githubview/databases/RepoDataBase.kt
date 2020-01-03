package com.example.githubview.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubview.dao.RepoDao
import com.example.githubview.entities.Repository
import com.example.githubview.entities.User

@Database(
    entities = [Repository::class, User::class],
    version = 1,
    exportSchema = false
)
abstract class RepoDataBase : RoomDatabase() {
    abstract fun repoDao(): RepoDao

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
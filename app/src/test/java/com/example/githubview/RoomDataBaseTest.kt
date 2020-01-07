package com.example.githubview

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.githubview.dao.RepoDao
import com.example.githubview.dao.UserDao
import com.example.githubview.databases.RepoDataBase
import com.example.githubview.responces.UserResponse
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomDataBaseTest {
    private lateinit var dbRepo: RepoDataBase
    private lateinit var userDao: UserDao
    private lateinit var repoDao: RepoDao

    @Before
    fun initDb() {
        dbRepo = Room.inMemoryDatabaseBuilder<RepoDataBase>(
            InstrumentationRegistry.getInstrumentation().context,
            RepoDataBase::class.java
        ).build()

        dbRepo.apply {
            userDao = userDao()
            repoDao = repoDao()
        }
    }

    @Test
    fun whenInsertEmployeeThenReadTheSameOne() = runBlocking {
        lateinit var dbEmployees: List<UserResponse>

        launch(Dispatchers.IO) {
            DbDataHelper.listOfUsers.forEach {
                userDao.insert(it)
            }
            dbEmployees = userDao.getAll()

        }.join()

        assertEquals(3, dbEmployees.size)
    }

    @Test
    fun whenInsert() = runBlocking {
        var usersIdFromDb: List<UserResponse>

        launch(Dispatchers.IO) {
            DbDataHelper.listOfUsers.forEach {
                userDao.insert(it)
            }
            usersIdFromDb = userDao.getAll()
            repoDao.insertAll(DbDataHelper.listOfRepos)
            println(usersIdFromDb[0].login)
            assertEquals(1, repoDao.getAllReposByLogin(usersIdFromDb[0].login).size)
            assertEquals(3, repoDao.getAllReposByLogin(usersIdFromDb[1].login).size)
            assertEquals(3, repoDao.getAllReposByLogin(usersIdFromDb[2].login).size)
        }.join()
    }

    @After
    fun closeDb() {
        dbRepo.close()
    }
}
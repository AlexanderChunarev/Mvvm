package com.example.githubview

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.githubview.dao.RepoDao
import com.example.githubview.dao.UserDao
import com.example.githubview.databases.RepoDataBase
import com.example.githubview.databases.UserDataBase
import com.example.githubview.entities.User
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomDataBaseTest {
    private lateinit var dbUser: UserDataBase
    private lateinit var dbRepo: RepoDataBase
    private lateinit var userDao: UserDao
    private lateinit var repoDao: RepoDao

    @Before
    fun initDb() {
        dbUser = Room.inMemoryDatabaseBuilder<UserDataBase>(
            InstrumentationRegistry.getInstrumentation().context,
            UserDataBase::class.java
        ).build()

        dbRepo = Room.inMemoryDatabaseBuilder<RepoDataBase>(
            InstrumentationRegistry.getInstrumentation().context,
            RepoDataBase::class.java
        ).build()

        userDao = dbUser.userDao()
        repoDao = dbRepo.repoDao()
    }

    @Test
    fun whenInsertEmployeeThenReadTheSameOne() = runBlocking {
        lateinit var dbEmployees: List<User>

        GlobalScope.launch(Dispatchers.IO) {
            DbDataHelper.listOfUsers.forEach {
                userDao.insert(it)
            }
            dbEmployees = userDao.getAll()

        }.join()

        assertEquals(3, dbEmployees.size)
    }

    @Test
    fun whenInsert() = runBlocking {
        var usersIdFromDb: List<User>

        GlobalScope.launch(Dispatchers.IO) {
            DbDataHelper.listOfUsers.forEach {
                userDao.insert(it)
            }
            usersIdFromDb = userDao.getAll()
            DbDataHelper.listOfRepos.forEach {
                repoDao.insert(it)
            }

            assertEquals(1, repoDao.getUsersWithPlaylists(usersIdFromDb[0].id).size)
            assertEquals(4, repoDao.getUsersWithPlaylists(usersIdFromDb[1].id).size)
            assertEquals(2, repoDao.getUsersWithPlaylists(usersIdFromDb[2].id).size)
        }.join()
    }

    @After
    fun closeDb() {
        dbUser.close()
    }
}
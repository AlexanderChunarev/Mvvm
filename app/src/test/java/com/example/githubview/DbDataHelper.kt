package com.example.githubview

import com.example.githubview.entities.Repository
import com.example.githubview.entities.User

object DbDataHelper {
    val listOfRepos = listOf(
        Repository(0, 3, "REPO_1", "Java", "11.11.11"),
        Repository(0, 2, "REPO_2", "Java", "11.11.11"),
        Repository(0, 2, "REPO_3", "Java", "11.11.11"),
        Repository(0, 2, "REPO_3", "Java", "11.11.11"),
        Repository(0, 3, "REPO_3", "Java", "11.11.11"),
        Repository(0, 2, "REPO_3", "Java", "11.11.11"),
        Repository(0, 1, "REPO_3", "Java", "11.11.11")
    )

    val listOfUsers = listOf(
        User(0, "user1", "url_user1", 3, 3),
        User(0, "user2", "url_user2", 23, 2),
        User(0, "user3", "url_user3", 2, 8)
    )
}
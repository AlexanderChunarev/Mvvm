package com.example.githubview

import com.example.githubview.entities.Owner
import com.example.githubview.responces.RepoResponse
import com.example.githubview.responces.UserResponse

object DbDataHelper {
    private val listOfOwners = listOf(
        Owner("user_1"),
        Owner("user_2"),
        Owner("user_3")
    )

    val listOfRepos = listOf(
        RepoResponse(1, listOfOwners[2], "REPO_1", "Java", "11.11.11"),
        RepoResponse(2, listOfOwners[1], "REPO_2", "Java", "11.11.11"),
        RepoResponse(3, listOfOwners[1], "REPO_3", "Java", "11.11.11"),
        RepoResponse(4, listOfOwners[1], "REPO_4", "Java", "11.11.11"),
        RepoResponse(5, listOfOwners[2], "REPO_5", "Java", "11.11.11"),
        RepoResponse(6, listOfOwners[2], "REPO_6", "Java", "11.11.11"),
        RepoResponse(7, listOfOwners[0], "REPO_7", "Java", "11.11.11")
    )

    val listOfUsers = listOf(
        UserResponse(1, "user_1", "url_user1", 3, 3),
        UserResponse(2, "user_2", "url_user2", 23, 2),
        UserResponse(3, "user_3", "url_user3", 2, 8)
    )
}
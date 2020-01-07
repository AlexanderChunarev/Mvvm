package com.example.githubview

import com.example.githubview.builder.ServiceBuilder
import com.example.githubview.extentions.await
import com.example.githubview.responces.RepoResponse
import com.example.githubview.responces.UserResponse
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock

class BuildServiceTest {
    @Mock
    private val serviceBuilder = ServiceBuilder()

    @Test
    fun build_service() {
        Assert.assertNotNull(serviceBuilder.gitHubService())
    }

    @Test
    fun userResponse_isNotNull() {
        val userResponse: UserResponse
        serviceBuilder.gitHubService().getUserInfo(LOGIN).await().apply {
            userResponse = this
        }

        Assert.assertNotNull(userResponse)
    }

    @Test
    fun repoResponse_isNotNull() {
        var repoResponse: List<RepoResponse>
        serviceBuilder.gitHubService().getRepoInfo(LOGIN).await().apply {
            repoResponse = this
        }
        Assert.assertNotNull(repoResponse)
    }

    companion object {
        private const val LOGIN = "AlexanderChunarev"
    }
}
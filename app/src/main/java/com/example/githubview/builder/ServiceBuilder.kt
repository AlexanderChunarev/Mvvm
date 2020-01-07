package com.example.githubview.builder

import com.example.githubview.config.APIConfiguration
import com.example.githubview.services.GitHubService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ServiceBuilder {
    private fun buildService(service: Class<GitHubService>): GitHubService {
        return Retrofit.Builder()
            .baseUrl(APIConfiguration.BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(
                        KotlinJsonAdapterFactory()
                    ).build()
                )
            )
            .client(okHttpClient.build())
            .build()
            .create(service)
    }

    private val okHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(
            HttpLoggingInterceptor().apply {
                connectTimeout(30, TimeUnit.SECONDS)
                readTimeout(30, TimeUnit.SECONDS)
                level = HttpLoggingInterceptor.Level.BODY
            })
    }

    fun gitHubService() = buildService(GitHubService::class.java)
}
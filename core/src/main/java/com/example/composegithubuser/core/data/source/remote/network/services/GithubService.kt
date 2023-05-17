package com.example.composegithubuser.core.data.source.remote.network.services

import com.example.composegithubuser.core.data.source.remote.response.GithubUserResponse
import com.example.composegithubuser.core.data.source.remote.response.SearchUserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @GET("/search/users")
    // Unlike RxJava, you don't need additional Call/Flowable Adapter configuration.
    // This is because Retrofit has supported Coroutines since Retrofit version 2.6.0.
    // You just change the function on ApiService to suspend function and you can call it.
    suspend fun getAllGithubUser(@Query("q") q: String): SearchUserResponse

    @GET("/users/{username}")
    suspend fun getDetailGithubUser(@Path("username") username: String): GithubUserResponse

    @GET("/users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String): List<GithubUserResponse>

    @GET("/users/{Username}/following")
    suspend fun getFollowing(@Path("username") username: String): List<GithubUserResponse>
}
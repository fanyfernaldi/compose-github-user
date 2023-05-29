package com.example.composegithubuser.core.domain.usecase

import com.example.composegithubuser.core.data.Resource
import com.example.composegithubuser.core.domain.model.GithubUser
import kotlinx.coroutines.flow.Flow

interface GithubUseCase {
    fun getAllGithubUser(q: String): Flow<Resource<List<GithubUser>>>

    fun getDetailUser(username: String): Flow<Resource<GithubUser>>

    fun getFollowers(username: String): Flow<Resource<List<GithubUser>>>

    fun getFollowing(username: String): Flow<Resource<List<GithubUser>>>

    fun getFavoriteUser(): Flow<List<GithubUser>>

    fun setFavoriteUser(githubUser: GithubUser, state: Boolean)
}
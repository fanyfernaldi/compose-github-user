package com.example.composegithubuser.core.data.source.local

import com.example.composegithubuser.core.data.source.local.entity.GithubUserEntity
import com.example.composegithubuser.core.data.source.local.room.GithubUserDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val githubUserDao: GithubUserDao) {

    fun getFavoriteGithubUser(): Flow<List<GithubUserEntity>> =
        githubUserDao.getFavoriteGithubUser()

    fun setFavoriteGithubUser(githubUser: GithubUserEntity, newState: Boolean) {
        githubUser.isFavorite = newState
        githubUserDao.updatedFavoriteGithubUser(githubUser)
    }

}
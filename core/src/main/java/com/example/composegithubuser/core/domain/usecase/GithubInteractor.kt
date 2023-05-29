package com.example.composegithubuser.core.domain.usecase

import com.example.composegithubuser.core.data.Resource
import com.example.composegithubuser.core.domain.model.GithubUser
import com.example.composegithubuser.core.domain.repository.IGithubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubInteractor @Inject constructor(private val githubRepository: IGithubRepository) :
    GithubUseCase {
    override fun getAllGithubUser(q: String): Flow<Resource<List<GithubUser>>> =
        githubRepository.getAllGithubUser(q)

    override fun getDetailUser(username: String): Flow<Resource<GithubUser>> =
        githubRepository.getDetailUser(username)

    override fun getFollowers(username: String): Flow<Resource<List<GithubUser>>> =
        githubRepository.getFollowers(username)

    override fun getFollowing(username: String): Flow<Resource<List<GithubUser>>> =
        githubRepository.getFollowing(username)

    override fun getFavoriteUser(): Flow<List<GithubUser>> =
        githubRepository.getFavoriteUser()

    override fun setFavoriteUser(githubUser: GithubUser, state: Boolean) =
        githubRepository.setFavoriteUser(githubUser, state)

}
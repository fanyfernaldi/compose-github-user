package com.example.composegithubuser.core.utils.mapper

import com.example.composegithubuser.core.data.source.local.entity.GithubUserEntity
import com.example.composegithubuser.core.data.source.remote.response.GithubUserResponse
import com.example.composegithubuser.core.domain.model.GithubUser

object GithubUserMapper {

    fun mapResponseToDomain(input: GithubUserResponse) = GithubUser(
        login = input.login ?: "",
        avatarUrl = input.avatarUrl ?: "",
        email = input.email ?: "",
        followers = input.followers ?: -1,
        following = input.following ?: -1,
        name = input.name ?: "",
        isFavorite = false
    )

    fun mapResponsesToDomain(input: List<GithubUserResponse>): List<GithubUser> =
        input.map {
            GithubUser(
                login = it.login ?: "",
                avatarUrl = it.avatarUrl ?: "",
                email = it.email ?: "",
                followers = it.followers ?: -1,
                following = it.following ?: -1,
                name = it.name ?: "",
                isFavorite = false
            )
        }

    fun mapEntitiesToDomain(input: List<GithubUserEntity>): List<GithubUser> =
        input.map {
            GithubUser(
                login = it.username,
                avatarUrl = it.avatarUrl,
                email = it.email,
                followers = it.followers,
                following = it.following,
                name = it.name,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: GithubUser) = GithubUserEntity(
        username = input.login,
        avatarUrl = input.avatarUrl,
        email = input.email,
        followers = input.followers,
        following = input.following,
        name = input.name,
        isFavorite = input.isFavorite
    )
}
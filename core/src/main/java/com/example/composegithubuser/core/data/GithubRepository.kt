package com.example.composegithubuser.core.data

import com.example.composegithubuser.core.data.source.local.LocalDataSource
import com.example.composegithubuser.core.data.source.remote.RemoteDataSource
import com.example.composegithubuser.core.data.source.remote.network.ApiResponse
import com.example.composegithubuser.core.domain.model.GithubUser
import com.example.composegithubuser.core.domain.repository.IGithubRepository
import com.example.composegithubuser.core.utils.AppExecutors
import com.example.composegithubuser.core.utils.mapper.GithubUserMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGithubRepository {
    override fun getAllGithubUser(q: String): Flow<Resource<List<GithubUser>>> {
        return flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getAllGithubUser(q).first()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(GithubUserMapper.mapResponsesToDomain(apiResponse.data)))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Success(listOf()))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        }
    }

    override fun getDetailUser(username: String): Flow<Resource<GithubUser>> {
        return flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getDetailGithubUser(username).first()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(GithubUserMapper.mapResponseToDomain(apiResponse.data)))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Error("no data"))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        }
    }

    override fun getFollowers(username: String): Flow<Resource<List<GithubUser>>> {
        return flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getFollowers(username).first()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(GithubUserMapper.mapResponsesToDomain(apiResponse.data)))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Success(listOf()))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        }
    }

    override fun getFollowing(username: String): Flow<Resource<List<GithubUser>>> {
        return flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getFollowing(username).first()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(GithubUserMapper.mapResponsesToDomain(apiResponse.data)))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Success(listOf()))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        }
    }

    override fun getFavoriteUser(): Flow<List<GithubUser>> {
        return localDataSource.getFavoriteGithubUser()
            .map { GithubUserMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteUser(githubUser: GithubUser, state: Boolean) {
        val githubUserEntity = GithubUserMapper.mapDomainToEntity(githubUser)
        appExecutors.diskIO()
            .execute { localDataSource.setFavoriteGithubUser(githubUserEntity, state) }
    }

}
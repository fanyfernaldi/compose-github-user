package com.example.composegithubuser.core.data.source.remote

import android.util.Log
import com.example.composegithubuser.core.data.source.remote.network.ApiResponse
import com.example.composegithubuser.core.data.source.remote.network.services.GithubService
import com.example.composegithubuser.core.data.source.remote.response.GithubUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val githubService: GithubService) {

    suspend fun getAllGithubUser(q: String): Flow<ApiResponse<List<GithubUserResponse>>> {
        Log.w("LOGW_TEST", "RemoteDataSource.getAllGithubUser")
        // get data from remote api
        // To create Flow, here we use flow builder (flow {})
        return flow {
            try {
                val response = githubService.getAllGithubUser(q)
                val users = response.users
                if (users.isNullOrEmpty()) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(response.users))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
            // we use Dispatcher.IO in flowOn because it is a process of fetching data from the server.
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailGithubUser(username: String): Flow<ApiResponse<GithubUserResponse>> {
        return flow {
            try {
                val response = githubService.getDetailGithubUser(username)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getFollowers(username: String): Flow<ApiResponse<List<GithubUserResponse>>> {
        return flow {
            try {
                val response = githubService.getFollowers(username)
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getFollowing(username: String): Flow<ApiResponse<List<GithubUserResponse>>> {
        return flow {
            try {
                val response = githubService.getFollowing(username)
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
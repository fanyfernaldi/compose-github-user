package com.example.composegithubuser.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.composegithubuser.core.data.source.local.entity.GithubUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubUserDao {
    @Query("SELECT * FROM github_user")
    fun getAllGithubUser(): Flow<List<GithubUserEntity>>

    @Query("SELECT * FROM github_user WHERE isFavorite = 1")
    fun getFavoriteGithubUser(): Flow<List<GithubUserEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertListGithubUser(listGithubUser: List<GithubUserEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGithubUser(githubUser: GithubUserEntity)

    @Update
    fun updatedFavoriteGithubUser(githubUser: GithubUserEntity)
}
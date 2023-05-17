package com.example.composegithubuser.core.di

import android.content.Context
import androidx.room.Room
import com.example.composegithubuser.core.data.source.local.room.GithubUserDao
import com.example.composegithubuser.core.data.source.local.room.GithubUserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// @Module is where we initialize the required objects
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    // @Provides contain how the object is created. Dagger doesn't care about the name of the function
    // What matters is the return value of the function
    @Singleton
    @Provides
    fun provideGithubUserDatabase(@ApplicationContext context: Context): GithubUserDatabase =
        Room.databaseBuilder(
            context,
            GithubUserDatabase::class.java, "GithubUser.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideGithubUserDao(database: GithubUserDatabase): GithubUserDao = database.githubUserDao()
}
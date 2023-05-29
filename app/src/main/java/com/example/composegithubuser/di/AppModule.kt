package com.example.composegithubuser.di

import com.example.composegithubuser.core.domain.usecase.GithubInteractor
import com.example.composegithubuser.core.domain.usecase.GithubUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

// we crate a new module to provide GithubUserUseCase
@Module
// we use the ViewModelComponent that Hilt has provided because the Module at the ViewModel level
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun provideGithubUseCase(githubInteractor: GithubInteractor): GithubUseCase
}
package com.example.composegithubuser.core.di

import com.example.composegithubuser.core.data.GithubRepository
import com.example.composegithubuser.core.data.source.remote.RemoteDataSource
import com.example.composegithubuser.core.domain.repository.IGithubRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// To create a Module inside a Module, you need to add the includes parameter
// By using this includes you can use the components in the NetworkModule and DatabaseModule in the RepositoryModule
// Apart from that, it also functions to make the division of Modules neater.
@Module(includes = [NetworkModule::class, DatabaseModule::class])
// if in the Dagger Component it determines which modules to use using @Component(modules = [...]),
// Hilt is the opposite, Module determines which Component to enter using @InstallIn
// Here RepositoryModule goes into SingletonComponent
// because it is the highest hierarchical Component that can be used anywhere
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    // We know that IGithubRepository is an interface, meaning it requires concrete implementation, in this case, GithubRepository.
    // That's why we need to define it here, the method is to create an abstract method with a parameter in the form of an implementation class (GithubRepository)
    // and a return in the form of an interface (IGithubRepository).
    // Also note that because the function is abstract, the class must also be abstract, that's why the module for @Binds is usually isolated
    // If you still want to combine it with @Provides you should make the other function static by adding the @JvmStatic annotation to Kotlin
    @Binds
    abstract fun provideGithubUserRepository(githubRepository: GithubRepository): IGithubRepository
}

// another way but the performance is not as good as using @Binds
// @Module(includes = [NetworkModule::class, DatabaseModule::class])
//class RepositoryModule {
//    @Singleton
//    @Provides
//    fun provideRepository(
//        remote: RemoteDataSource,
//        local: LocalDataSource,
//        executors: AppExecutors
//    ) : IGithubRepository = GithubRepository(remote)
//}
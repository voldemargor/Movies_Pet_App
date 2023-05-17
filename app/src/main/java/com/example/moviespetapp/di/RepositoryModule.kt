package com.example.moviespetapp.di

import com.example.moviespetapp.data.RepositoryImpl
import com.example.moviespetapp.data.TempRepositoryImpl
import com.example.moviespetapp.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    //abstract fun bindRepository(repository: TempRepositoryImpl): Repository
    abstract fun bindRepository(repository: RepositoryImpl): Repository

}
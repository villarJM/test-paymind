package com.devvillar.testpaymind.core.di

import com.devvillar.testpaymind.feature.auth.data.repositories.AuthRepositoryImpl
import com.devvillar.testpaymind.feature.auth.domain.repositories.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}
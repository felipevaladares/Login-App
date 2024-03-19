package com.felpster.loginapp.di

import com.felpster.loginapp.data.LoginRepositoryImpl
import com.felpster.loginapp.domain.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginModule {
    @Binds
    abstract fun bindsUserRepository(it: LoginRepositoryImpl): LoginRepository
}

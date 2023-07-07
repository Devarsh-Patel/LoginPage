package com.example.login.repository

import com.example.login.model.database.SignUpDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSignUpRepository(signUpDao: SignUpDao):SignUpRepository {
        return SignUpRepositoryImpl(signUpDao)
    }
}
package com.example.login.model.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideSignUpDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app,SignUpDatabase::class.java,"sign_up_database").build()
    @Singleton
    @Provides
    fun provideSignUpDao(db: SignUpDatabase): SignUpDao {
        return db.singUpDao()
    }
}
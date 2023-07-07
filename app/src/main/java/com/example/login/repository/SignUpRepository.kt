package com.example.login.repository


import com.example.login.model.database.SignUpDao
import com.example.login.model.database.SignUpData

interface SignUpRepository {


    suspend fun addProfile(signUpData: SignUpData): Long

    suspend fun updateProfile(signUpData: SignUpData)

    suspend fun getByUsername(username: String): SignUpData?

    suspend fun deleteProfile(signUpData: SignUpData)

/*
    suspend fun deleteAllProfile(signUpData: SignUpData)
*/
}
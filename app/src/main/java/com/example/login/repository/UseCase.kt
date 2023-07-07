package com.example.login.repository

import com.example.login.model.database.SignUpData

interface UseCase {

    suspend fun addProfile(signUpData: SignUpData): Long
    suspend fun getByUsername(username: String): SignUpData?
    suspend fun updateUser(signUpData: SignUpData)
}
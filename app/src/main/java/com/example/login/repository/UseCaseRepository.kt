package com.example.login.repository

import com.example.login.model.database.SignUpData
import javax.inject.Inject

class UseCaseRepository @Inject constructor (private var signUpRepository: SignUpRepository): UseCase {

    override suspend fun addProfile(signUpData: SignUpData): Long {
        return signUpRepository.addProfile(signUpData)
    }

    override suspend fun getByUsername(username: String): SignUpData? {
        return signUpRepository.getByUsername(username)
    }

    override suspend fun updateUser(signUpData: SignUpData) {
        return signUpRepository.updateProfile(signUpData)
    }
}
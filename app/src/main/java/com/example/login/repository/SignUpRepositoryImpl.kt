package com.example.login.repository


import androidx.lifecycle.LiveData
import com.example.login.model.database.SignUpDao
import com.example.login.model.database.SignUpData
import javax.inject.Inject

class SignUpRepositoryImpl
    @Inject constructor(private val signUpDao: SignUpDao): SignUpRepository {

    //val readAllData: LiveData<List<SignUpData>> = signUpDao.getAll()

    override suspend fun addProfile(signUpData: SignUpData): Long {
        return signUpDao.insert(signUpData)
    }

    override suspend fun updateProfile(signUpData: SignUpData) {
        signUpDao.update(signUpData)
    }

    override suspend fun getByUsername(username: String): SignUpData? {
        return signUpDao.getByUsername(username)
    }

    override suspend fun deleteProfile(signUpData: SignUpData){
        signUpDao.delete(signUpData)
    }

  /*  override suspend fun deleteAllProfile(signUpData: SignUpData){
        signUpDao.deleteAll(signUpData.userName)
    }
*/
}
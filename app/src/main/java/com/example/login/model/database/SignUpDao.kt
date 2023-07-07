package com.example.login.model.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SignUpDao {
    @Query("SELECT * from Sing_Up")
    fun getAll(): LiveData<List<SignUpData>>

    @Query("SELECT * from Sing_Up where user_name LIKE :userName")
    suspend fun getByUsername(userName: String): SignUpData?

    @Query("SELECT * FROM Sing_Up WHERE user_name LIKE :name AND password LIKE :password")
    fun readAllData(name: String, password: String): LiveData<SignUpData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(signUpData: SignUpData): Long

    @Update
    suspend fun update(signUpData:SignUpData)

    @Delete
    suspend fun delete(profile:SignUpData): Int

//    @Query("DELETE FROM Sing_Up where user_name = :userName")
//    suspend fun deleteAll(userName: String): Int
}
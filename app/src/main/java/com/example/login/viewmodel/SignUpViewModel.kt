package com.example.login.viewmodel

import androidx.lifecycle.*
import com.example.login.model.database.SignUpData
import com.example.login.repository.UseCaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel
    @Inject constructor (private val repository: UseCaseRepository):
    ViewModel() {

    fun addProfile(signUpData: SignUpData) {
        viewModelScope.launch() {
            try {
                repository.addProfile(signUpData)
            } catch (e: Exception){
                println(e.message)
            }
        }
    }
}
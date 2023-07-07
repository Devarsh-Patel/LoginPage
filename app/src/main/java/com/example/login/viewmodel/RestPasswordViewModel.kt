package com.example.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.model.database.SignUpData
import com.example.login.repository.UseCaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestPasswordViewModel
@Inject constructor (private val repository: UseCaseRepository):
    ViewModel() {


        fun updateUser(signUpData: SignUpData){
            viewModelScope.launch() {
                try {
                    repository.updateUser(signUpData)
                } catch (e: Exception){
                    println(e.message)
                }
            }
        }
}
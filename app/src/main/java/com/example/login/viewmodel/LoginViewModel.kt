package com.example.login.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.model.database.SignUpData
import com.example.login.repository.SignUpRepository
import com.example.login.repository.UseCaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject constructor(private val repository: UseCaseRepository):
    ViewModel() {

    private val _user = MutableLiveData<SignUpData>()
    val user: LiveData<SignUpData> = _user


    fun getByUserPassword(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getByUsername(username)
                _user.postValue(response)
            } catch (exception: Exception){
                _user.value = null
            }
        }
    }
}
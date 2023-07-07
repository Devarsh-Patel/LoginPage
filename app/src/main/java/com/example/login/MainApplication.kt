package com.example.login

import android.app.Application
import com.example.login.model.database.SignUpDatabase
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext

@HiltAndroidApp
class MainApplication: Application() {
    val database by lazy {
        SignUpDatabase.getInstance(this.applicationContext)
    }
}
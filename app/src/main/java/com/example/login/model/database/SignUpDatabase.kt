package com.example.login.model.database

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper


@Database(entities = [SignUpData::class], version = 1, exportSchema = false)
abstract class SignUpDatabase: RoomDatabase() {

    abstract fun singUpDao(): SignUpDao

    companion object {
        @Volatile
        private var INSTANCE: SignUpDatabase? = null

        fun getInstance(context: Context): SignUpDatabase {
           return INSTANCE ?: synchronized(this) {
               Room.databaseBuilder(
                        context.applicationContext,
                        SignUpDatabase::class.java,
                        "sign_up_database"
                    )
                   .fallbackToDestructiveMigration()
                   .build()
                   .also { INSTANCE = it }

            }
        }
    }


}
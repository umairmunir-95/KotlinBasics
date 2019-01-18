package com.tallymarks.kotlinbasics.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Users::class], version = 1)
abstract class DatabaseHandler : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        var INSTANCE:DatabaseHandler? = null
        val DATABASE_NAME = "KotlinDB"
        const val USERS_TABLE = "USERS_TABLE"

        fun getAppDataBase(context: Context): DatabaseHandler? {
            if (INSTANCE == null){
                synchronized(DatabaseHandler::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, DatabaseHandler::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}


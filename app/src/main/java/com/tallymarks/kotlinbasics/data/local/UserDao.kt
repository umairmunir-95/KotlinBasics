package com.tallymarks.kotlinbasics.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface UserDao {

    @get:Query("Select * from " + DatabaseHandler.USERS_TABLE)
    val allUsers: List<Users>

    @Insert
    fun insertUser(users: Users)

    @Query("Delete from " + DatabaseHandler.USERS_TABLE)
    fun truncateTable()

}

package com.tallymarks.kotlinbasics.data.local

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = DatabaseHandler.USERS_TABLE)
class Users(var userName: String?, var designation: String?) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

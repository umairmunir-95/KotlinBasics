package com.tallymarks.kotlinbasics.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserRepoModel {
    @SerializedName("name")
    @Expose
    var name: String? = null
}

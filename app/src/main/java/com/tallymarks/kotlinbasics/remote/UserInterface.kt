package com.tallymarks.kotlinbasics.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface UserInterface {
    @GET
    fun getUserRepos(@Url url: String): Call<List<UserRepoModel>>
}

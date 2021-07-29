package com.example.fine_dust_app.data.retrofit_builder

import com.example.fine_dust_app.data.model.GithubInfo
import retrofit2.Call
import retrofit2.http.GET

interface GithubAPI {

    @GET("users/HYE0N1127")
    fun getGithubInfo(): Call<GithubInfo>
}
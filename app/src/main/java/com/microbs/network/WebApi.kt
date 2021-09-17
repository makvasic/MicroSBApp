package com.microbs.network

import com.microbs.model.Repo

import retrofit2.http.GET
import retrofit2.http.Path

interface WebApi {

    @GET("users/{user}/repos")
    suspend fun getReposForUser(@Path("user") user: String): List<Repo>?
}

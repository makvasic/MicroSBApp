package com.microbs.network

import com.microbs.model.Retro

import retrofit2.http.GET
import retrofit2.http.Path

interface WebApi {

    @GET("users/{user}/repos")
    suspend fun getRetrosForUser(@Path("user") user: String): List<Retro>?
}

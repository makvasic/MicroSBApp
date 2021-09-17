package com.microbs.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object WebApiFactory {

    val webApi: WebApi by lazy {
        val client = OkHttpClient().newBuilder()
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        retrofit.create(WebApi::class.java)
    }

}

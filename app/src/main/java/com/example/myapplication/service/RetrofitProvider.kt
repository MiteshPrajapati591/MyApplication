package com.example.myapplication.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitProvider {

    private val client: OkHttpClient =  OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .build()


    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URLFactory.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}
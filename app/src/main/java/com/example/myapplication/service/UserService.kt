package com.example.myapplication.service

import io.reactivex.Single
import retrofit2.http.GET

interface UserService {

    //GET
    @GET(URLFactory.Method.GET_USER)
    fun getUser(): Single<Any>
}
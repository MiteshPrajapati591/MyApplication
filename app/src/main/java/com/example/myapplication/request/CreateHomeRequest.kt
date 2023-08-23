package com.example.myapplication.request

import com.google.gson.annotations.SerializedName

data class CreateHomeRequest (

    @SerializedName("page")
    var page: String? = null,

    @SerializedName("size")
    var size: String? = null
)
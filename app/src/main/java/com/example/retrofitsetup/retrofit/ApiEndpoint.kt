package com.lazday.kotlinandroidretrofit.retrofit

import com.example.retrofitsetup.MainModel
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint {

    @GET("data.php")
    fun data(): Call<MainModel>
}
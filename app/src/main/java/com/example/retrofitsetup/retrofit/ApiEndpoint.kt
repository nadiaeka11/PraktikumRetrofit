package com.example.retrofitsetup.retrofit

import com.example.retrofitsetup.MainModel
import retrofit2.Call
import retrofit2.http.GET

// Interface untuk mendefinisikan endpoint-endpoint API
interface   ApiEndpoint {

    // Anotasi GET untuk menandai bahwa ini adalah request HTTP GET
    @GET("data.php")
    // Fungsi untuk mengambil data dari endpoint "data.php"
    fun getData(): Call<MainModel>
}
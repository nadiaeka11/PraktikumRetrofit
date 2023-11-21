package com.example.retrofitsetup.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Objek Kotlin (singleton) untuk menyediakan instance Retrofit
object ApiService {

    // URL dasar dari API
    private const val BASE_URL: String = "https://demo.lazday.com/rest-api-sample/"

    // Properti dengan lazy initialization untuk menyediakan instance dari ApiEndpoint
    @JvmStatic
    val endpoint: ApiEndpoint by lazy {

        // Interceptor untuk logging HTTP request dan response
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        // OkHttpClient yang menggunakan interceptor untuk logging
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        // Retrofit Builder untuk konfigurasi Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl( BASE_URL ) // Converter untuk Gson
            .addConverterFactory(GsonConverterFactory.create()) // Menggunakan OkHttpClient yang sudah dikonfigurasi
            .build()

        // Membuat dan mengembalikan instance ApiEndpoint yang sesuai dengan definisi interface
        retrofit.create( ApiEndpoint::class.java )
    }
}
package com.example.retrofitsetup

// Kelas data yang merepresentasikan model data utama
data class MainModel (
    val result: ArrayList<Result> // Properti yang berisi daftar hasil (result)
) {
    // Kelas data yang merepresentasikan entitas hasil (result)
    data class Result (val id: Int, val title: String, val image: String)
}
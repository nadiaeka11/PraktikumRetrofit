package com.example.retrofitsetup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitsetup.retrofit.ApiService
import com.example.retrofitsetup.MainModel
import com.example.retrofitsetup.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"

    lateinit var movieAdapter: MovieAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set judul ActionBar
        supportActionBar?.let {
            it.title = "Avengers"
        }
        // Inisialisasi RecyclerView dan mendapatkan data dari API
        setupRecyclerView()
        getDataFromApi()
    }

    // Mengatur RecyclerView
    private fun setupRecyclerView(){
        // Membuat objek adapter dan menetapkannya ke RecyclerView
        movieAdapter = MovieAdapter(arrayListOf(), object : MovieAdapter.OnAdapterListener {
            override fun onClick(result: MainModel.Result) {
                // Menangani klik pada item RecyclerView, membuka DetailActivity dengan mengirim data
                startActivity(
                    Intent(this@MainActivity, DetailActivity::class.java)
                        .putExtra("intent_title", result.title)
                        .putExtra("intent_image", result.image)
                )
            }
        })

        // Menetapkan layoutManager dan adapter ke RecyclerView
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter
        }
    }

    // Mendapatkan data dari API menggunakan Retrofit
    private fun getDataFromApi(){
        // Menampilkan ProgressBar saat memuat data
        binding.progressBar.visibility = View.VISIBLE

        // Menggunakan Retrofit untuk mendapatkan data dari API
        ApiService.endpoint.getData().enqueue(object : Callback<MainModel> {
                override fun onFailure(call: Call<MainModel>, t: Throwable) {
                    // Menyembunyikan ProgressBar jika terjadi kegagalan
                    binding.progressBar.visibility = View.GONE
                    printLog( "onFailure: $t" )
                }

                override fun onResponse(call: Call<MainModel>, response: Response<MainModel>) {
                    // Menyembunyikan ProgressBar setelah mendapatkan respons dari API
                    binding.progressBar.visibility = View.GONE

                    if (response.isSuccessful && response.body() != null) {
                        // Menampilkan data di RecyclerView jika respons berhasil
                        showData( response.body()!! )
                    }
                }
            })
    }

    // Mencetak pesan log dengan tag "MainActivity"
    private fun printLog(message: String) {
        Log.d(TAG, message)
    }

    // Menampilkan data di RecyclerView menggunakan adapter
    private fun showData(data: MainModel) {
        val results = data.result
        movieAdapter.setData( results )
    }
}

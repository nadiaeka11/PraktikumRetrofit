package com.example.retrofitsetup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.retrofitsetup.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menggunakan View Binding untuk menghubungkan layout dengan aktivitas
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mendapatkan data dari intent
        val intentTitle = intent.getStringExtra("intent_title")
        val intentImage = intent.getStringExtra("intent_image")

        // Menetapkan judul ActionBar dengan data dari intent
        supportActionBar?.title = intentTitle

        // Menggunakan Glide untuk memuat gambar dari URL ke ImageView
        Glide.with(this)
            .load(intentImage)
            .placeholder(R.drawable.img_placeholder) // Gambar placeholder saat sedang memuat
            .error(R.drawable.img_placeholder) // Gambar yang ditampilkan jika terjadi kesalahan
            .into(binding.imageView) // Menetapkan gambar ke ImageView
    }
}
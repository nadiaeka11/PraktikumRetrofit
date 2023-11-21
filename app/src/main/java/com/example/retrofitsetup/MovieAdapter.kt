package com.example.retrofitsetup

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlin.collections.ArrayList
import com.example.retrofitsetup.databinding.AdapterMovieBinding

class MovieAdapter (
    private val results : ArrayList<MainModel.Result> ,
    private val listener: OnAdapterListener)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    // Menginflate layout adapter_movie.xml dan membuat ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    // Mendapatkan jumlah item dalam data set
    override fun getItemCount() = results.size

    // Menetapkan data untuk item di posisi tertentu
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        // Mengikat data ke ViewHolder
        holder.bind(result)
        // Menambahkan OnClickListener untuk item RecyclerView
        holder.itemView.setOnClickListener {
            // Memanggil fungsi onClick pada listener jika item diklik
            listener.onClick(result)
        }
    }

    // ViewHolder berisi referensi ke elemen UI untuk setiap item
    class ViewHolder(private val binding: AdapterMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Mengikat data ke elemen UI dalam ViewHolder
        fun bind(result: MainModel.Result) {
            // Menetapkan teks untuk TextView
            binding.textView.text = result.title
            // Log untuk mencatat URL gambar
            Log.d("MovieAdapter", "resultImage: ${result.image}")
            // Menggunakan Glide untuk memuat gambar dari URL ke ImageView
            Glide.with( binding.root )
                .load(result.image)
                .placeholder(R.drawable.img_placeholder) // Gambar placeholder saat sedang memuat
                .error(R.drawable.img_placeholder) // Gambar yang ditampilkan jika terjadi kesalahan
                .centerCrop()
                .into( binding.imageView ) // Menetapkan gambar ke ImageView
        }
    }

    // Menetapkan data baru ke adapter
    fun setData(data: ArrayList<MainModel.Result>){
        this.results.clear()
        this.results.addAll(data)
        notifyDataSetChanged()
    }

    // Antarmuka untuk menangani kejadian klik pada item RecyclerView
    interface OnAdapterListener {
        fun onClick(result: MainModel.Result)
    }
}
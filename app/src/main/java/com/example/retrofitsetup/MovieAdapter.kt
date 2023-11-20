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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.bind(result)
        holder.itemView.setOnClickListener {
            listener.onClick(result)
        }
    }

    class ViewHolder(private val binding: AdapterMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: MainModel.Result) {
        binding.textView.text = result.title
        Log.d("MovieAdapter", "resultImage: ${result.image}")
        Glide.with( binding.root )
            .load(result.image)
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .centerCrop()
            .into( binding.imageView )
        }
    }

    fun setData(data: ArrayList<MainModel.Result>){
        this.results.clear()
        this.results.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(result: MainModel.Result)
    }
}
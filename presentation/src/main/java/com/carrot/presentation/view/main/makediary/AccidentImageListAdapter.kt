package com.carrot.presentation.view.main.makediary

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carrot.presentation.databinding.ItemAccidentImageListBinding

class AccidentImageListAdapter() : RecyclerView.Adapter<AccidentImageListAdapter.ViewHolder>() {

    private val items = mutableListOf<String>()
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val binding =
            ItemAccidentImageListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccidentImageListAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(val binding: ItemAccidentImageListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUri: String) {
            Glide.with(binding.root).load(Uri.parse(imageUri)).into(binding.imageViewAccidentImage)
        }
    }

    fun addItems(imageList: List<String>) {
        this.items.clear()
        this.items.addAll(imageList)
    }
}
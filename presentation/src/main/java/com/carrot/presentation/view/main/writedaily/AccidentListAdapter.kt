package com.carrot.presentation.view.main.writedaily

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carrot.presentation.databinding.ItemAccidentListBinding
import com.carrot.presentation.model.Accident
import com.carrot.presentation.view.main.makediary.AccidentImageListAdapter

class AccidentListAdapter() : RecyclerView.Adapter<AccidentListAdapter.ViewHolder>() {

    private val accidents = mutableListOf<Accident>()


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): AccidentListAdapter.ViewHolder {
        val binding =
            ItemAccidentListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(accidents[position])
    }

    override fun getItemCount(): Int {
        return accidents.size
    }

    class ViewHolder(val binding: ItemAccidentListBinding) : RecyclerView.ViewHolder(binding.root) {
        private val imageListAdapter = AccidentImageListAdapter()
        fun bind(accident: Accident) {
            binding.textViewAccidentContextItemAccidentList.text = accident.content
            binding.recyclerViewAccidentImageListAccidentMakeDialog.adapter = imageListAdapter
            imageListAdapter.addItems(accident.imageList)
            imageListAdapter.notifyDataSetChanged()

        }
    }

    fun addAccident(accident: List<Accident>) {
        this.accidents.clear()
        this.accidents.addAll(accident)
    }
}
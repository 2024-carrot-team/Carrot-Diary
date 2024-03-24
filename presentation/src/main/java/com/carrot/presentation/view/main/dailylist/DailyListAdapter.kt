package com.carrot.presentation.view.main.dailylist

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carrot.presentation.databinding.ItemDailyListBinding
import com.carrot.presentation.databinding.ItemDailyListDateBinding
import com.carrot.presentation.model.DailyHeader


// viewtype
private const val VIEW_TYPE_HEADER = 0
private const val VIEW_TYPE_CONTAINER = 1

class DailyListAdapter(
    private val context: Context,
    val onItemClickListener: ((DailyHeader) -> Unit),
    val onDeleteClickListener: ((Int,Int) -> Unit),
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dailyListItem = mutableListOf<Any>() //diary

    fun deleteItem(position: Int){
        dailyListItem.removeAt(position)
        notifyDataSetChanged()
    }

    fun clearAllData(){
        dailyListItem.clear()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(dailyHeaderList: List<DailyHeader>) {
        // Date로 그룹핑된 Map
        val dateGroups = dailyHeaderList.groupBy { it.yearMonth }

        // {Date1, Daily, Daily, Date2, Daily, Daily, .... (Daily 중 가장 첫 번째꺼 출력!)
        dateGroups.entries.forEach { entry ->
            entry.key.let { dailyListItem.add(it) }
            dailyListItem.addAll(entry.value)
        }

        dailyListItem.forEach { item ->
            Log.d("dailyListItem.forEach", item.toString())
        }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                DailyListDateViewHolder(
                    ItemDailyListDateBinding.inflate(
                        inflater,
                        parent,
                        false
                    )
                )
            }
            // VIEW_TYPE_CONTAINER
            else -> DailyListContainerViewHolder(
                ItemDailyListBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
        }
    }


    override fun getItemViewType(position: Int): Int {

        return when (dailyListItem[position]) {
            is String -> VIEW_TYPE_HEADER
            else -> VIEW_TYPE_CONTAINER // Daily
        }
    }


    override fun getItemCount(): Int {
        return dailyListItem.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DailyListDateViewHolder -> {
                val item = dailyListItem[position] as String?
                holder.bind(
                    item = item
                )
            }

            is DailyListContainerViewHolder -> {
                val item = dailyListItem[position] as DailyHeader
                holder.bind(
                    item,
                    position = position,
                    onItemClickListener = onItemClickListener,
                    onDeleteClickListener = onDeleteClickListener,
                )
            }
        }
    }


    class DailyListDateViewHolder(private val itemBinding: ItemDailyListDateBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: String?) {
            itemBinding.dateTvDailyList.text = item
        }
    }

    class DailyListContainerViewHolder(private val itemBinding: ItemDailyListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: DailyHeader, position: Int, onItemClickListener: (DailyHeader) -> Unit, onDeleteClickListener: ((Int,Int) -> Unit) ) {
            itemBinding.containerTvDily.text = item.content
            itemBinding.dayTvDailyList.text = item.day
            itemBinding.deleteIbDailyList.setOnClickListener { onDeleteClickListener.invoke(item.postDiaryId, position) }
            itemBinding.root.setOnClickListener { onItemClickListener.invoke(item) }
        }
    }
}
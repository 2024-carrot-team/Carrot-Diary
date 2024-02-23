package com.carrot.presentation.view.main.dailylist

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carrot.presentation.databinding.ItemDailyListBinding
import com.carrot.presentation.databinding.ItemDailyListDateBinding
import com.carrot.presentation.model.Daily
import com.carrot.presentation.model.Diary
import java.util.Date


// viewtype
private const val VIEW_TYPE_HEADER = 0
private const val VIEW_TYPE_CONTAINER = 1

class DailyListAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dailyListItem = mutableListOf<Any>() //diary

    fun updateDataset(diary: Diary) {

        // Date로 그룹핑된 Map
        val dateGroups = diary.dailyList.groupBy { it.date }

        // {Date1, Daily, Daily, Date2, Daily, Daily, .... (Daily 중 가장 첫 번째꺼 출력!)
        dateGroups.entries.forEach { entry ->
            entry.key?.let { dailyListItem.add(it) }
            dailyListItem.addAll(entry.value)
        }

        dailyListItem.forEach { item ->
            Log.d("ddddddd", item.toString())
        }


        // update DataSet
        dailyListItem.addAll(dailyListItem)
        notifyDataSetChanged() // to dailyListItem
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        Log.d("ddddddd", "onCreateViewHolder")
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
        Log.d("ddddddd", "getItemViewType")

        return when (dailyListItem[position]) {
            is Date? -> VIEW_TYPE_HEADER
            else -> VIEW_TYPE_CONTAINER // Daily
        }
    }


    override fun getItemCount(): Int {
        return dailyListItem.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DailyListDateViewHolder -> {
                val item = dailyListItem[position] as Date?
                holder.bind(item)
                Log.d("ddddddd", "onBindViewHolder")

            }

            is DailyListContainerViewHolder -> {
                val item = dailyListItem[position] as Daily
                holder.bind(item)
            }

        }

    }


    class DailyListDateViewHolder(private val itemBinding: ItemDailyListDateBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Date?) {

            itemBinding.dateTvDailyList.text = "1월"
            Log.d("ddddddd", "DailyListDateViewHolder")

            //itemBinding.executePendingBindings()

        }
    }

    class DailyListContainerViewHolder(private val itemBinding: ItemDailyListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Daily) {

            itemBinding.containerTvDily.text = item.accidents[0].toString()
            itemBinding.dayTvDailyList.text = "23일"

            Log.d("list", "container")

            //itemBinding.executePendingBindings()
        }
    }
}
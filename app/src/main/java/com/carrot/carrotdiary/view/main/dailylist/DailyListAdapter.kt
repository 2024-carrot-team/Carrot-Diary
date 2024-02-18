package com.carrot.carrotdiary.view.main.dailylist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carrot.carrotdiary.databinding.ItemDailyListBinding
import com.carrot.carrotdiary.databinding.ItemDailyListDateBinding
import com.carrot.carrotdiary.model.Diary


// viewtype
private const val VIEW_TYPE_DATE = 0
private const val VIEW_TYPE_CONTAINER = 1

class DailyListAdapter(private val diaryList: List<Diary>, private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_DATE -> {
                DailyListContainerViewHolder(
                    ItemDailyListBinding.inflate(
                        inflater,
                        parent,
                        false
                    )
                )
            }
            // VIEW_TYPE_CONTAINER
            else -> DailyListDateViewHolder(
                ItemDailyListDateBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
        }
    }

//    override fun getItemViewType(position: Int): Int {
//        return when (diaryList[position]) {
//            is -> VIEW_TYPE_DATE
//            is -> VIEW_TYPE_CONTAINER
//        }
//    }


    override fun getItemCount(): Int {
        return diaryList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DailyListContainerViewHolder -> { // sealed 상속관계로...
//                val item = diaryList[position] as  ?? // 타입변환
//                holder.bind(item)
            }

            is DailyListDateViewHolder -> {
//                val item = diaryList[position] as  ??
//                holder.bind(item)
            }

        }

    }


    class DailyListContainerViewHolder(private val itemBinding: ItemDailyListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind() {


            //itemBinding.executePendingBindings()

        }

    }

    class DailyListDateViewHolder(private val itemBinding: ItemDailyListDateBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind() {


            //itemBinding.executePendingBindings()

        }
    }

}
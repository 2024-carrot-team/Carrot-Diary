package com.carrot.carrotdiary.view.main.dailylist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.carrot.carrotdiary.databinding.FragmentDailyListBinding
import com.carrot.carrotdiary.model.Accident
import com.carrot.carrotdiary.model.Daily
import com.carrot.carrotdiary.model.Diary
import com.carrot.carrotdiary.model.User
import java.text.SimpleDateFormat
import java.util.Date

class DailyListFragment : Fragment() {
    private lateinit var binding: FragmentDailyListBinding
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDailyListBinding.inflate(inflater, container, false)

        if (container != null) context = container.context

        // Dummy user data
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val birthDate = dateFormat.parse("1990-01-01")
        val user =
            User("1", "example@example.com", "password123", "user123", birthDate, "profile.jpg")

        // Dummy accidents
        val accident1 =
            Accident(1, "Car accident", listOf("image1.jpg", "image2.jpg"), "2024-02-18")
        val accident2 = Accident(2, "Slipped on ice", listOf("image3.jpg"), "2024-02-19")

        // Dummy dailies
        val daily1 = Daily(1, Date(), 10, listOf(accident1))
        val daily2 = Daily(2, Date(), 15, listOf(accident2))
        val daily3 = Daily(3, Date(), 1, listOf(accident1, accident2))

        // Dummy diary
        val diaryDate = dateFormat.format(Date())
        val diary =
            Diary(1, "My Daily Diary", user, listOf(daily1, daily2, daily3), "cover.jpg", diaryDate)


        val adapter = DailyListAdapter(context)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        adapter.updateDataset(diary)

        return binding.root
    }

}
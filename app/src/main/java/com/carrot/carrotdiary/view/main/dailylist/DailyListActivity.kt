package com.carrot.carrotdiary.view.main.dailylist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.carrot.carrotdiary.databinding.ActivityDailyListBinding
import com.carrot.carrotdiary.model.Accident
import com.carrot.carrotdiary.model.Daily
import com.carrot.carrotdiary.model.Diary
import com.carrot.carrotdiary.model.User
import java.text.SimpleDateFormat
import java.util.Date

class DailyListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDailyListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyListBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        val date1 = Date()
        val date2 =
            Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24) // Adding 1 day to current date
        val date3 =
            Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 2) // Adding 2 days to current date

        val daily1 = Daily(1, date1, 10, listOf(accident1))
        val daily2 = Daily(2, date2, 15, listOf(accident2))
        val daily3 = Daily(3, date3, 1, listOf(accident1, accident2))

        // Dummy diary
        val diaryDate = dateFormat.format(Date())
        val diary =
            Diary(1, "My Daily Diary", user, listOf(daily1, daily2, daily3), "cover.jpg", diaryDate)


        val adapter = DailyListAdapter(this)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter.updateDataset(diary)
    }
}
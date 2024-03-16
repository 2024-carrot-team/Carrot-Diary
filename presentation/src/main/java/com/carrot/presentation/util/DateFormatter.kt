package com.carrot.presentation.util

class DateFormatter {

    fun dateToString(date: String) {
        val date_parts = date.split("T")
        val year_month_day = date_parts[0].split("-")
        val month = year_month_day[1]
        val day = year_month_day[2]
    }
}
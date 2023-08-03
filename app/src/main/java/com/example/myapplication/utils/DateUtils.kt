package com.example.myapplication.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by [Andreas Happy H] on 8/2/2023
 * Jakarta, Indonesia.
 */
object DateUtils {
    fun getCurrentHourInIndonesia(): String {
        val timeZone = TimeZone.getTimeZone("Asia/Jakarta")
        val calendar = Calendar.getInstance(timeZone)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val formatter = SimpleDateFormat("HH:mm")
        return formatter.format(calendar.time)
    }

    fun getCurrentDateFormatted(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat(
            "dd MMMM yyyy", Locale("id", "ID")
        ) // "id" for Indonesia, "ID" for the country variant
        return dateFormat.format(calendar.time)
    }
}
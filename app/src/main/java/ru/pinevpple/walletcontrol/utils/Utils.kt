package ru.pinevpple.walletcontrol.utils

import java.text.SimpleDateFormat
import java.util.*

const val STD_DATE_FORMAT = "dd.MM.yyyy"
const val STD_TIME_FORMAT = "HH:mm"
const val SQL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm"

object Utils {

    fun formattedDate(date: String, time: String, format: String = SQL_DATE_TIME_FORMAT): String {
        val inputDateTime = "$date $time"
        val inputFormat = "dd.MM.yyyy HH:mm"
        val dateObject = SimpleDateFormat(inputFormat, Locale.US).parse(inputDateTime)
        return SimpleDateFormat(format, Locale.US).format(dateObject!!)
    }

    fun currentDateAndTime(
        dateFormat: String = STD_DATE_FORMAT,
        timeFormat: String = STD_TIME_FORMAT
    ): Pair<String, String> {
        val c = Calendar.getInstance()
        val currentDateAndTime = c.time
        val date = SimpleDateFormat(dateFormat, Locale.US).format(currentDateAndTime)
        val time = SimpleDateFormat(timeFormat, Locale.US).format(currentDateAndTime)
        return date to time
    }
}
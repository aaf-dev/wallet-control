package ru.pinevpple.walletcontrol.utils

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun formattedDate(date: String, time: String, format: String = "yyyy-MM-ddTHH:mm"): String {
        val inputDateTime = "$date $time"
        val inputFormat = "dd.MM.yyyy HH:mm"
        val dateObject = SimpleDateFormat(inputFormat, Locale.US).parse(inputDateTime)
        return SimpleDateFormat(format, Locale.US).format(dateObject!!)
    }

    fun currentDateAndTime(): Pair<String, String> {
        val c = Calendar.getInstance()
        val currentDateAndTime = c.time
        val sdfDate = SimpleDateFormat("dd.MM.yyyy", Locale.US).format(currentDateAndTime)
        val sdfTime = SimpleDateFormat("HH:mm", Locale.US).format(currentDateAndTime)
        return sdfDate to sdfTime
    }
}
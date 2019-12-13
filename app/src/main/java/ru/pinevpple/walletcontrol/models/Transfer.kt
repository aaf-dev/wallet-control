package ru.pinevpple.walletcontrol.models

import java.util.*

interface Transfer {
    fun transferDate(date: Date)
    fun transferTime(time: Date)
}
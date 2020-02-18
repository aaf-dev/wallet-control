package ru.pinevpple.walletcontrol.main.domain

import ru.pinevpple.walletcontrol.main.model.MainData

class Converter {

    fun getMainData(incomeAndExpense: Pair<Int, Int>): MainData {
        val (income, expense) = incomeAndExpense
        val balance = income - expense
        return MainData(income, expense, balance)
    }
}
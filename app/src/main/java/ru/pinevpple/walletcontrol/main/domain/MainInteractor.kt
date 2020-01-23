package ru.pinevpple.walletcontrol.main.domain

import ru.pinevpple.walletcontrol.main.model.MainData
import ru.pinevpple.walletcontrol.main.repository.DatabaseRepository

class MainInteractor(
    private val repository: DatabaseRepository
) {

    fun getMainData(): MainData {
        val income = getTotalIncomes()
        val expense = getTotalExpenses()
        val balance = income - expense
        return toMainData(income, expense, balance)
    }

    private fun getTotalIncomes(): Int =
        repository.getTotalIncomes().value ?: 0

    private fun getTotalExpenses(): Int =
        repository.getTotalExpenses().value ?: 0

    private fun toMainData(income: Int, expense: Int, balance: Int): MainData =
        MainData(income, expense, balance)
}
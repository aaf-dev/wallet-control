package ru.pinevpple.walletcontrol.main.model

import java.text.NumberFormat
import java.util.*

data class MainData (
    private var income: Int? = 0,
    private var expense: Int? = 0,
    private var currency: Int? = 0
) {
    fun setIncome(income: Int?) {
        this.income = income
    }

    fun getIncomeString(): String {
        val localIncome = income ?: 0
        val rawIncomeString = NumberFormat.getNumberInstance(Locale.US).format(localIncome)
        return rawIncomeString.replace(',', ' ')
    }

    fun setExpense(expense: Int?) {
        this.expense = expense
    }

    fun getExpenseString(): String {
        val localExpense = expense ?: 0
        val rawExpenseString = NumberFormat.getNumberInstance(Locale.US).format(localExpense)
        return rawExpenseString.replace(',', ' ')
    }

    fun getBalanceString(): String {
        val localIncome = income ?: 0
        val localExpense = expense ?: 0
        val localBalance = localIncome - localExpense
        val rawBalanceString = NumberFormat.getNumberInstance(Locale.US).format(localBalance)
        return rawBalanceString.replace(',', ' ')
    }

    fun setCurrency(currency: Int) {
        this.currency = currency
    }

    fun getCurrency(): Int = currency ?: 0
}
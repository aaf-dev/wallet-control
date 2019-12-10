package ru.pinevpple.walletcontrol.models

data class GeneralInfo (
    private var income: Int? = 0,
    private var expense: Int? = 0,
    private var currency: Int? = 0
) {
    fun setIncome(income: Int?) {
        this.income = income
    }

    fun getIncome(): Int = income ?: 0

    fun setExpense(expense: Int?) {
        this.expense = expense
    }

    fun getExpense(): Int = expense ?: 0

    fun setCurrency(currency: Int) {
        this.currency = currency
    }

    fun getCurrency(): Int = currency ?: 0
}
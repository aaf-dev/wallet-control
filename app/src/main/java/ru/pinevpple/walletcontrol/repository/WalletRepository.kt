package ru.pinevpple.walletcontrol.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.pinevpple.walletcontrol.models.Dao
import ru.pinevpple.walletcontrol.models.ExpenseTable
import ru.pinevpple.walletcontrol.models.IncomeTable
import java.util.*

class WalletRepository(
    private val incomeDao: Dao.IncomeDao?,
    private val expenseDao: Dao.ExpenseDao?) {

    val incomeList: LiveData<List<IncomeTable>>? = incomeDao?.incomeDataListSortedByDate()
    val expenseList: LiveData<List<ExpenseTable>>? = expenseDao?.expenseDataListSortedByDate()
    val incomeSum: LiveData<Int>? = incomeDao?.getSum()
    val expenseSum: LiveData<Int>? = expenseDao?.getSum()

    suspend fun insertIncome(income: IncomeTable) {
        incomeDao?.insert(income)
    }
}
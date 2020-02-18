package ru.pinevpple.walletcontrol.repository

import androidx.lifecycle.LiveData
import io.reactivex.Flowable
import ru.pinevpple.walletcontrol.db.model.BillsTable
import ru.pinevpple.walletcontrol.db.dao.Dao
import ru.pinevpple.walletcontrol.db.model.ExpenseTable
import ru.pinevpple.walletcontrol.db.model.IncomeTable

class WalletRepository (
    private val incomeDao: Dao.IncomeDao? = null,
    private val expenseDao: Dao.ExpenseDao? = null,
    private val billsDao: Dao.BillsDao? = null) {

    val incomeList: LiveData<List<IncomeTable>>? = incomeDao?.getListSortedByDate()
    val expenseList: LiveData<List<ExpenseTable>>? = expenseDao?.getListSortedByDate()
    val billsList: LiveData<List<BillsTable>>? = billsDao?.billsListSortedByPriority()
    val incomeTotalSum: LiveData<Int>? = incomeDao?.getTotalSum()
    val expenseTotalSum: LiveData<Int>? = expenseDao?.getTotalSum()

    fun getIncomeListInPeriod(from: String, to: String): LiveData<List<IncomeTable>>? = incomeDao?.getListInPeriodSortedByDate(from, to)
    fun getExpenseListInPeriod(from: String, to: String): LiveData<List<ExpenseTable>>? = expenseDao?.getListInPeriodSortedByDate(from, to)
    fun getIncomeSumInPeriod(from: String, to: String): LiveData<Int>? = incomeDao?.getSumInPeriod(from, to)
    fun getExpenseSumInPeriod(from: String, to: String): LiveData<Int>? = expenseDao?.getSumInPeriod(from, to)

    suspend fun insertIncome(income: IncomeTable) {
        incomeDao?.insert(income)
    }

    suspend fun insertExpense(expense: ExpenseTable) {
        expenseDao?.insert(expense)
    }

    suspend fun insertBill(bill: BillsTable) {
        billsDao?.insert(bill)
    }
}
package ru.pinevpple.walletcontrol.main.repository

import androidx.lifecycle.LiveData
import ru.pinevpple.walletcontrol.db.dao.Dao
import ru.pinevpple.walletcontrol.db.model.BillsTable

class DatabaseRepository(
    private val incomeDao: Dao.IncomeDao,
    private val expenseDao: Dao.ExpenseDao,
    private val billsDao: Dao.BillsDao
    ){

    fun getTotalIncomes(): LiveData<Int> =
        incomeDao.getTotalSum()

    fun getTotalExpenses(): LiveData<Int> =
        expenseDao.getTotalSum()

    fun getBills(): LiveData<List<BillsTable>> =
        billsDao.billsListSortedByPriority()

}
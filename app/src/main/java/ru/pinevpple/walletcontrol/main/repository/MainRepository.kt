package ru.pinevpple.walletcontrol.main.repository

import io.reactivex.Flowable
import io.reactivex.Maybe
import ru.pinevpple.walletcontrol.db.model.BillsTable
import ru.pinevpple.walletcontrol.db.model.ExpenseTable
import ru.pinevpple.walletcontrol.db.model.IncomeTable
import ru.pinevpple.walletcontrol.main.db.MainDao
import ru.pinevpple.walletcontrol.main.model.Operation

class MainRepository(
    private val dao: MainDao
    ){
    fun getAmountOfIncomes(): Maybe<Int> =
        dao.getAmountOfIncomes()

    fun getAmountOfExpenses(): Maybe<Int> =
        dao.getAmountOfExpenses()

    fun getListOfIncomes(): Flowable<List<IncomeTable>> =
        dao.getListOfIncomes()

    fun getListOfExpenses(): Flowable<List<ExpenseTable>> =
        dao.getListOfExpenses()

    fun getListOfBills(): Flowable<List<BillsTable>> =
        dao.getListOfBills()

    fun getListOfIncomesAndExpenses(): Flowable<List<Operation>> =
        dao.getListOfIncomesAndExpenses()
}
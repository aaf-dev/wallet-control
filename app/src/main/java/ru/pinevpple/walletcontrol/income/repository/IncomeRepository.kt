package ru.pinevpple.walletcontrol.income.repository

import io.reactivex.Completable
import ru.pinevpple.walletcontrol.db.model.IncomeTable
import ru.pinevpple.walletcontrol.income.db.IncomeDao

class IncomeRepository(
    private val incomeDao: IncomeDao
) {

    fun addNewIncome(income: IncomeTable): Completable =
        incomeDao.addIncome(income)
}
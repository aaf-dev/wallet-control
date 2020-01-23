package ru.pinevpple.walletcontrol.income.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.pinevpple.walletcontrol.db.model.IncomeTable
import ru.pinevpple.walletcontrol.db.WalletDatabase
import ru.pinevpple.walletcontrol.repository.WalletRepository

class AddIncomeViewModel(app: Application) : AndroidViewModel(app) {

    private val db: WalletDatabase = WalletDatabase.getDatabase(app)
    private val incomeDao = db.incomeDao()
    private val repository: WalletRepository = WalletRepository(incomeDao)

    fun insertIncome(income: IncomeTable) = viewModelScope.launch {
        repository.insertIncome(income)
    }
}
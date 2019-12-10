package ru.pinevpple.walletcontrol.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.pinevpple.walletcontrol.models.IncomeTable
import ru.pinevpple.walletcontrol.models.WalletDatabase
import ru.pinevpple.walletcontrol.repository.WalletRepository

class IncomeAddViewModel(app: Application) : AndroidViewModel(app) {

    private val repository: WalletRepository

    init {
        val incomeDao = WalletDatabase.getDatabase(app).incomeDao()
        repository = WalletRepository(incomeDao, null)
    }

    fun insertIncome(income: IncomeTable) = viewModelScope.launch {
        repository.insertIncome(income)
    }
}
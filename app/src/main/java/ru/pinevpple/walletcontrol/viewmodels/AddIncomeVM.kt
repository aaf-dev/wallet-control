package ru.pinevpple.walletcontrol.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.pinevpple.walletcontrol.models.IncomeTable
import ru.pinevpple.walletcontrol.models.WalletDatabase
import ru.pinevpple.walletcontrol.repository.WalletRepository

class AddIncomeVM(app: Application) : AndroidViewModel(app) {

    private val db: WalletDatabase = WalletDatabase.getDatabase(app)
    private val incomeDao = db.incomeDao()
    private val repository: WalletRepository = WalletRepository(incomeDao, null)


    fun insertIncome(income: IncomeTable) = viewModelScope.launch {
        repository.insertIncome(income)
    }
}
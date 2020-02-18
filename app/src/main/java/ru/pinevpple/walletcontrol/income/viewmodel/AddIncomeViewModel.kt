package ru.pinevpple.walletcontrol.income.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.Completable
import kotlinx.coroutines.launch
import ru.pinevpple.walletcontrol.db.model.IncomeTable
import ru.pinevpple.walletcontrol.db.WalletDatabase
import ru.pinevpple.walletcontrol.income.repository.IncomeRepository
import ru.pinevpple.walletcontrol.repository.WalletRepository

class AddIncomeViewModel(app: Application) : AndroidViewModel(app) {

    private val db: WalletDatabase = WalletDatabase.getDatabase(app)
    private val dao = db.getIncomeDao()
    private val repository: IncomeRepository = IncomeRepository(dao)

    fun addIncome(income: IncomeTable): Completable =
        repository.addNewIncome(income)
 }
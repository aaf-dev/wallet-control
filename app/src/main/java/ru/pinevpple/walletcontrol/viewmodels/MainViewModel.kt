package ru.pinevpple.walletcontrol.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import ru.pinevpple.walletcontrol.models.GeneralInfo
import ru.pinevpple.walletcontrol.models.IncomeTable
import ru.pinevpple.walletcontrol.models.WalletDatabase
import ru.pinevpple.walletcontrol.repository.WalletRepository

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val db: WalletDatabase = WalletDatabase.getDatabase(app)
    private val incomeDao = db.incomeDao()
    private val expenseDao = db.expenseDao()
    private val repository: WalletRepository = WalletRepository(incomeDao, expenseDao)
    private val incomeSum: LiveData<Int>? = repository.incomeSum
    private val expenseSum: LiveData<Int>? = repository.expenseSum
    private val info: GeneralInfo = GeneralInfo(income = 0, expense = 0)

    val incomeList: LiveData<List<IncomeTable>>? = repository.incomeList
    val infoData: MutableLiveData<GeneralInfo> = MutableLiveData()
    val mediator: MediatorLiveData<Int> = MediatorLiveData()

    init {
        infoData.value = info
        incomeSum?.let { income ->
            mediator.addSource(income) {
                info.setIncome(it)
                infoData.value = info
            }
        }
        expenseSum?.let { expense ->
            mediator.addSource(expense) {
                info.setExpense(it)
                infoData.value = info
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        db.close()
    }
}
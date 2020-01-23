package ru.pinevpple.walletcontrol.main.viewmodel

import android.app.Application
import androidx.lifecycle.*
import ru.pinevpple.walletcontrol.db.model.BillsTable
import ru.pinevpple.walletcontrol.main.model.MainData
import ru.pinevpple.walletcontrol.db.model.IncomeTable
import ru.pinevpple.walletcontrol.db.WalletDatabase
import ru.pinevpple.walletcontrol.repository.WalletRepository

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val db: WalletDatabase = WalletDatabase.getDatabase(app)
    private val incomeDao = db.incomeDao()
    private val expenseDao = db.expenseDao()
    private val billsDao = db.billsDao()
    private val repository: WalletRepository = WalletRepository(incomeDao, expenseDao, billsDao)
    private val incomeSum: LiveData<Int>? = repository.incomeTotalSum
    private val expenseSum: LiveData<Int>? = repository.expenseTotalSum
    private val info: MainData =
        MainData(
            income = 0,
            expense = 0
        )

    val incomeList: LiveData<List<IncomeTable>>? = repository.incomeList
    val billsList: LiveData<List<BillsTable>>? = repository.billsList

    val infoData: MutableLiveData<MainData> = MutableLiveData()
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
}
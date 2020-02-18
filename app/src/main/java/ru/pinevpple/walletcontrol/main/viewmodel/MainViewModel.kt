package ru.pinevpple.walletcontrol.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import ru.pinevpple.walletcontrol.db.model.BillsTable
import ru.pinevpple.walletcontrol.main.model.MainData
import ru.pinevpple.walletcontrol.db.WalletDatabase
import ru.pinevpple.walletcontrol.db.model.ExpenseTable
import ru.pinevpple.walletcontrol.db.model.IncomeTable
import ru.pinevpple.walletcontrol.main.domain.Converter
import ru.pinevpple.walletcontrol.main.model.Operation
import ru.pinevpple.walletcontrol.main.repository.MainRepository

class MainViewModel(app: Application) : AndroidViewModel(app) {

    val mainDataTest = BehaviorSubject.create<MainData>().toSerialized()

    private val db: WalletDatabase = WalletDatabase.getDatabase(app)
    private val dao = db.getMainDao()
    private val repository: MainRepository = MainRepository(dao)
    private val converter = Converter()
    private val compositeDisposable = CompositeDisposable()

    init {
        Flowable.zip(
            getAmountOfIncomes()
                .defaultIfEmpty(0)
                .toFlowable(),
            getAmountOfExpenses()
                .defaultIfEmpty(0)
                .toFlowable(),
            BiFunction<Int, Int, Pair<Int, Int>> { incomes: Int, expenses: Int ->
                incomes to expenses
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ pair ->
                converter.getMainData(pair).let {
                    mainDataTest.onNext(it)
                }
            }, { error -> throw error }
            ).let { compositeDisposable.add(it) }
    }

    fun getListOfIncomesAndExpenses(): Flowable<List<Operation>> =
        repository.getListOfIncomesAndExpenses()

    fun getListOfBills(): Flowable<List<BillsTable>> =
        repository.getListOfBills()

    fun getListOfIncomes(): Flowable<List<IncomeTable>> =
        repository.getListOfIncomes()

    fun  getListOfExpenses(): Flowable<List<ExpenseTable>> =
        repository.getListOfExpenses()

    private fun getAmountOfIncomes(): Maybe<Int> =
        repository.getAmountOfIncomes()

    private fun getAmountOfExpenses(): Maybe<Int> =
        repository.getAmountOfExpenses()

        override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
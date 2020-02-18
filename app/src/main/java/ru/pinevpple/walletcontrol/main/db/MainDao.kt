package ru.pinevpple.walletcontrol.main.db

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Maybe
import ru.pinevpple.walletcontrol.db.model.BillsTable
import ru.pinevpple.walletcontrol.db.model.ExpenseTable
import ru.pinevpple.walletcontrol.db.model.IncomeTable
import ru.pinevpple.walletcontrol.main.model.Operation

private const val INCOME_TABLE = "income_table"
private const val EXPENSE_TABLE = "expense_table"
private const val BILLS_TABLE = "bills_table"

@Dao
interface MainDao {
    @Query("SELECT SUM(amount) from $INCOME_TABLE")
    fun getAmountOfIncomes(): Maybe<Int>

    @Query("SELECT SUM(amount) from $EXPENSE_TABLE")
    fun getAmountOfExpenses(): Maybe<Int>

    @Query("SELECT * from $INCOME_TABLE")
    fun getListOfIncomes(): Flowable<List<IncomeTable>>

    @Query("SELECT * from $EXPENSE_TABLE")
    fun getListOfExpenses(): Flowable<List<ExpenseTable>>

    @Query("SELECT * from $BILLS_TABLE")
    fun getListOfBills(): Flowable<List<BillsTable>>

    @Query("SELECT * from $INCOME_TABLE, $EXPENSE_TABLE")
    fun getListOfIncomesAndExpenses(): Flowable<List<Operation>>
}
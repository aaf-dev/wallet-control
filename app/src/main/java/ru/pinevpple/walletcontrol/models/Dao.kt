package ru.pinevpple.walletcontrol.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

private const val INCOME_TABLE = "income_table"
private const val EXPENSE_TABLE = "expense_table"

object Dao {

    @Dao
    interface IncomeDao {
        @Query("SELECT * from $INCOME_TABLE ORDER BY date ASC")
        fun incomeDataListSortedByDate(): LiveData<List<IncomeTable>>

        @Query("SELECT SUM(amount) from $INCOME_TABLE")
        fun getSum(): LiveData<Int>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(income: IncomeTable)

        @Query("DELETE FROM $INCOME_TABLE")
        suspend fun deleteAll()
    }

    @Dao
    interface ExpenseDao {
        @Query("SELECT * from $EXPENSE_TABLE ORDER BY date ASC")
        fun expenseDataListSortedByDate(): LiveData<List<ExpenseTable>>

        @Query("SELECT SUM(amount) from $EXPENSE_TABLE")
        fun getSum(): LiveData<Int>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(income: ExpenseTable)

        @Query("DELETE FROM $EXPENSE_TABLE")
        suspend fun deleteAll()
    }
}
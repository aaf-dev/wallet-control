package ru.pinevpple.walletcontrol.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.pinevpple.walletcontrol.db.model.BillsTable
import ru.pinevpple.walletcontrol.db.model.ExpenseTable
import ru.pinevpple.walletcontrol.db.model.IncomeTable

private const val INCOME_TABLE = "income_table"
private const val EXPENSE_TABLE = "expense_table"
private const val BILLS_TABLE = "bills_table"

object Dao {

    @Dao
    interface IncomeDao {
        @Query("SELECT * from $INCOME_TABLE ORDER BY date ASC")
        fun getListSortedByDate(): LiveData<List<IncomeTable>>

        @Query("SELECT * from $INCOME_TABLE where date between :fromDate and :toDate ORDER BY date ASC")
        fun getListInPeriodSortedByDate(fromDate: String, toDate: String): LiveData<List<IncomeTable>>

        @Query("SELECT SUM(amount) from $INCOME_TABLE")
        fun getTotalSum(): LiveData<Int>

        @Query("SELECT SUM(amount) from $INCOME_TABLE where date between :fromDate and :toDate")
        fun getSumInPeriod(fromDate: String, toDate: String): LiveData<Int>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(income: IncomeTable)

        @Query("DELETE FROM $INCOME_TABLE")
        suspend fun deleteAll()
    }

    @Dao
    interface ExpenseDao {
        @Query("SELECT * from $EXPENSE_TABLE ORDER BY date ASC")
        fun getListSortedByDate(): LiveData<List<ExpenseTable>>

        @Query("SELECT * from $EXPENSE_TABLE where date between :fromDate and :toDate ORDER BY date ASC")
        fun getListInPeriodSortedByDate(fromDate: String, toDate: String): LiveData<List<ExpenseTable>>

        @Query("SELECT SUM(amount) from $EXPENSE_TABLE")
        fun getTotalSum(): LiveData<Int>

        @Query("SELECT SUM(amount) from $EXPENSE_TABLE where date between :fromDate and :toDate")
        fun getSumInPeriod(fromDate: String, toDate: String): LiveData<Int>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(income: ExpenseTable)

        @Query("DELETE FROM $EXPENSE_TABLE")
        suspend fun deleteAll()
    }

    @Dao
    interface BillsDao {
        @Query("SELECT * from $BILLS_TABLE ORDER BY priority ASC")
        fun billsListSortedByPriority(): LiveData<List<BillsTable>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(bill: BillsTable)
    }
}
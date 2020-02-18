package ru.pinevpple.walletcontrol.income.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import ru.pinevpple.walletcontrol.db.model.IncomeTable

private const val INCOME_TABLE = "income_table"

@Dao
interface IncomeDao {
    @Query("SELECT * FROM $INCOME_TABLE ORDER BY date DESC")
    fun test(): Flowable<List<IncomeTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addIncome(income: IncomeTable): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(income: IncomeTable)

    @Query("DELETE FROM $INCOME_TABLE")
    suspend fun deleteAll()
}
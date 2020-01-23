package ru.pinevpple.walletcontrol.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.pinevpple.walletcontrol.db.dao.Dao
import ru.pinevpple.walletcontrol.db.model.ExpenseTable
import ru.pinevpple.walletcontrol.db.model.IncomeTable

@Database(entities = [IncomeTable::class, ExpenseTable::class], version = 1, exportSchema = false)
abstract class WalletDatabase : RoomDatabase() {

    abstract fun incomeDao(): Dao.IncomeDao
    abstract fun expenseDao(): Dao.ExpenseDao
    abstract fun billsDao(): Dao.BillsDao

    companion object {
        @Volatile
        private var instance: WalletDatabase? = null

        fun getDatabase(context: Context): WalletDatabase {
            val tempInstance =
                instance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val localInstance = Room.databaseBuilder(
                    context.applicationContext,
                    WalletDatabase::class.java,
                    "wallet_db"
                ).build()
                instance = localInstance
                return localInstance
            }
        }
    }
}
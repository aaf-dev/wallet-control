package ru.pinevpple.walletcontrol.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [IncomeTable::class, ExpenseTable::class], version = 1, exportSchema = false)
abstract class WalletDatabase : RoomDatabase() {

    abstract fun incomeDao(): Dao.IncomeDao
    abstract fun expenseDao(): Dao.ExpenseDao

    companion object {
        @Volatile
        private var instance: WalletDatabase? = null

        fun getDatabase(context: Context): WalletDatabase {
            val tempInstance = instance
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
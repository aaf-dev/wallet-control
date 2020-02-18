package ru.pinevpple.walletcontrol.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.pinevpple.walletcontrol.db.model.BillsTable
import ru.pinevpple.walletcontrol.db.model.ExpenseTable
import ru.pinevpple.walletcontrol.db.model.IncomeTable
import ru.pinevpple.walletcontrol.income.db.IncomeDao
import ru.pinevpple.walletcontrol.main.db.MainDao

@Database(
    entities = [IncomeTable::class, ExpenseTable::class, BillsTable::class],
    version = 2,
    exportSchema = false
)
abstract class WalletDatabase : RoomDatabase() {

    abstract fun getMainDao(): MainDao
    abstract fun getIncomeDao(): IncomeDao

    companion object {
        @Volatile
        private var instance: WalletDatabase? = null

        fun getDatabase(context: Context): WalletDatabase {
            val tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                Room
                    .databaseBuilder(
                        context.applicationContext,
                        WalletDatabase::class.java,
                        "wallet_db"
                    )
                    .build()
                    .let {
                        instance = it
                        return it
                    }
            }
        }
    }
}
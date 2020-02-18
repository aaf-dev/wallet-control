package ru.pinevpple.walletcontrol.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bills_table")
class BillsTable(
    @ColumnInfo(name = "name") val title: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "balance") val balance: Int,
    @ColumnInfo(name = "priority") val priority: Int
) {
    @PrimaryKey(autoGenerate = true)
    private var id: Int = 0

    fun getId(): Int = id
    fun setId(id: Int) {
        this.id = id
    }
}

enum class BillType(val label: String) {
    CASH("Cash"),
    DEBIT("Debit"),
    CREDIT("Credit"),
    DEPOSIT("Deposit");
}
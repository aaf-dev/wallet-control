package ru.pinevpple.walletcontrol.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "income_table")
class IncomeTable(@ColumnInfo(name = "amount") val amount: Float,
                  @ColumnInfo(name = "date") val date: String) {

    @PrimaryKey(autoGenerate = true)
    private var id: Int = 0

    fun getId(): Int = id
    fun setId(id: Int) {
        this.id = id
    }
}
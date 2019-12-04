package ru.pinevpple.walletcontrol.models

data class GeneralInfo (
    val income: Int = 0,
    val expense: Int = 0,
    val balance: Int = income - expense,
    val currency: Int = 0
) {
}
package domain.model.account

import domain.model.account.currency.Currency

data class Account(
    val accountId: Long = 0,
    val userId: Long = 0,
    val employeeId: Long = 0,
    val accountNumber: String = "",
    val availableBalance: Double = 0.0,
    val reservedAmount: Double = 0.0,
    val creationDate: Long = 0,
    val expireDate: Long = 0,
    val active: Boolean = true,
    val currency: Currency = Currency(),
    val accountType: String = ""
)
package domain.model.account

import domain.model.account.currency.Currency

data class Account(
    val accountId: Long,
    val userId: Long,
    val employeeId: Long,
    val accountNumber: String,
    val availableBalance: Double,
    val reservedAmount: Double,
    val creationDate: Long,
    val expireDate: Long,
    val active: Boolean,
    val currency: Currency,
    val accountType: String
)
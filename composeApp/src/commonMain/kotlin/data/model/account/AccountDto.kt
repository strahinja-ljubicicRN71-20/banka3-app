package data.model.account

import data.model.account.currency.CurrencyDto
import kotlinx.serialization.Serializable

@Serializable
data class AccountDto(
    val accountId: Long,
    val userId: Long,
    val employeeId: Long,
    val accountNumber: String,
    val availableBalance: Double,
    val reservedAmount: Double,
    val creationDate: Long,
    val expireDate: Long,
    val active: Boolean,
    val currency: CurrencyDto,
    val accountType: String
)
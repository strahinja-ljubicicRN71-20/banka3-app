package data.model.account.currency

import kotlinx.serialization.Serializable

@Serializable
data class CurrencyDto(
    val currencyId: Long,
    val name: String,
    val mark: String
)

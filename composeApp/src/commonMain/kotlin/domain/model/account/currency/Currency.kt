package domain.model.account.currency

import kotlinx.serialization.Serializable

@Serializable
data class Currency(
    val currencyId: Long = 0,
    val name: String = "",
    val mark: String = ""
)
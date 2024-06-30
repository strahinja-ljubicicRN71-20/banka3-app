package data.model.transaction

import kotlinx.serialization.Serializable

@Serializable
data class TransactionDto(
    val accountFrom: String,
    val accountTo: String,
    val amount: Double,
    val sifraPlacanja: Int,
    val pozivNaBroj: String?,
    val date: Long
)

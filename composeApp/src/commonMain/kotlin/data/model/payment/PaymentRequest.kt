package data.model.payment

import kotlinx.serialization.Serializable

@Serializable
data class PaymentRequest(
    val accountFrom: String = "",
    val accountTo: String = "",
    val amount: Double = 0.0,
    val currencyMark: String = "",
    val sifraPlacanja: Int = 0,
    val pozivNaBroj: String = ""
)

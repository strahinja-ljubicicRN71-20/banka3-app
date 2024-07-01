package data.model.payment

import kotlinx.serialization.Serializable

@Serializable
data class ConfirmPaymentRequest(
    val transactionId: Long,
    val code: Long
)

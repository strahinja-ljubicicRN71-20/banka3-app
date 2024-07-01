package data.model.payment

import kotlinx.serialization.Serializable

@Serializable
data class PaymentResponse(
    val transactionId: Long = 0,
)

package domain.model.payment

import domain.model.account.Account
import kotlinx.serialization.Serializable

@Serializable
data class PaymentScreenInfo(
    val account: Account = Account(),
)
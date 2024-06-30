package data.model.account

import kotlinx.serialization.Serializable

@Serializable
data class AccountsDto(
    val accounts: List<AccountDto>
)
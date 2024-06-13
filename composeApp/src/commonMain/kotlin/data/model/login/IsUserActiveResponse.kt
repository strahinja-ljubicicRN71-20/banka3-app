package data.model.login

import kotlinx.serialization.Serializable

@Serializable
data class IsUserActiveResponse(
    val email: String,
    val codeActive: Boolean
)
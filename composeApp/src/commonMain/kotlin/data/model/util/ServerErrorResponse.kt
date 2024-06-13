package data.model.util

import kotlinx.serialization.Serializable

@Serializable
data class ServerErrorResponse(
    val message: String? = null
)
package domain.model.user

import kotlinx.serialization.Serializable


@Serializable
data class User(
    val firstName: String = "",
    val lastName: String = "",
    val jmbg: String = "",
    val dateOfBirth: Long = 0,
    val phoneNumber: String = "",
    val address: String? = "",
    val email: String = "",
    val id: String = ""
)
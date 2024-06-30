package data.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserInfoDto(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val jmbg: String = "",
    val dateOfBirth: Long = 0,
    val gender: String = "",
    val phoneNumber: String = "",
    val address: String? = "",
    val email: String = "",
)
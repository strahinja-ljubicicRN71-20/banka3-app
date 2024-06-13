package domain.model.login

data class IsUserActiveResult(
    val isSuccessful: Boolean = false,
    val email: String = "",
    val codeActive: Boolean = false
)
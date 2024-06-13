package domain.model

data class IsUserActiveResult(
    val email: String = "",
    val codeActive: Boolean = false
)
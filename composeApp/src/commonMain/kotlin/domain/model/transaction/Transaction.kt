package domain.model.transaction

data class Transaction(
    val accountFrom: String,
    val accountTo: String,
    val amount: Double,
    val sifraPlacanja: Int,
    val pozivNaBroj: String?,
    val date: Long
)

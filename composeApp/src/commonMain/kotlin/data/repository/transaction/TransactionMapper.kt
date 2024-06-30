package data.repository.transaction

import data.model.transaction.TransactionDto
import domain.model.transaction.Transaction

class TransactionMapper {
    fun map(value: List<TransactionDto>): List<Transaction> {
        return value.map { transactionDto ->
            Transaction(
                accountFrom = transactionDto.accountFrom,
                accountTo = transactionDto.accountTo,
                amount = transactionDto.amount,
                sifraPlacanja = transactionDto.sifraPlacanja,
                pozivNaBroj = transactionDto.pozivNaBroj,
                date = transactionDto.date
            )
        }
    }
}
package domain.repository

import domain.model.RepositoryResponse
import domain.model.transaction.Transaction

interface ITransactionRepository {
    suspend fun getTransactionsForAccount(accountNumber: String): RepositoryResponse<List<Transaction>>
}
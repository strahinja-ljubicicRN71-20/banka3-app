package domain.usecase.transaction

import domain.model.RepositoryResponse
import domain.model.transaction.Transaction
import domain.repository.ITransactionRepository

class GetAccountTransactionsUseCase(
    private val transactionRepository: ITransactionRepository
) {
    suspend operator fun invoke(accountNumber: String): List<Transaction> {
        return when(val response = transactionRepository.getTransactionsForAccount(accountNumber)) {
            is RepositoryResponse.Error -> emptyList()
            is RepositoryResponse.Success -> response.body
        }
    }
}
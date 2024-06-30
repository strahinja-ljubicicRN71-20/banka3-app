package data.repository.transaction

import data.model.transaction.TransactionDto
import data.util.safeRequest
import domain.model.RepositoryResponse
import domain.model.transaction.Transaction
import domain.repository.ITransactionRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.url
import io.ktor.http.HttpMethod

class TransactionRepository(
    private val httpClient: HttpClient,
    private val transactionMapper: TransactionMapper
): ITransactionRepository {
    override suspend fun getTransactionsForAccount(accountNumber: String): RepositoryResponse<List<Transaction>> {

        val response = httpClient.safeRequest<List<TransactionDto>> {
            url("v1/transaction/getAllPaymentTransactions/$accountNumber")
            method = HttpMethod.Get
        }

        return when (response) {
            is RepositoryResponse.Error -> response
            is RepositoryResponse.Success -> RepositoryResponse.Success(transactionMapper.map(response.body))
        }
    }
}
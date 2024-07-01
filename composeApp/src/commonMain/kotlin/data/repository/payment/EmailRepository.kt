package data.repository.payment

import data.model.payment.ConfirmPaymentRequest
import data.util.safeRequest
import domain.model.RepositoryResponse
import domain.repository.IEmailRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpMethod

class EmailRepository(
    private val httpClient: HttpClient
): IEmailRepository {
    override suspend fun finishTransaction(confirmPaymentRequest: ConfirmPaymentRequest): RepositoryResponse<Boolean> {
        val reposnse = httpClient.safeRequest<Boolean> {
            url("v1/transaction/confirm")
            method = HttpMethod.Post
            setBody(confirmPaymentRequest)
        }

        return when (reposnse) {
            is RepositoryResponse.Error -> reposnse
            is RepositoryResponse.Success -> RepositoryResponse.Success(reposnse.body)
        }
    }
}
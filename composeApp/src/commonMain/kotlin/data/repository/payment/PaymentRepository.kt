package data.repository.payment

import data.model.payment.PaymentRequest
import data.model.payment.PaymentResponse
import data.util.safeRequest
import domain.model.RepositoryResponse
import domain.repository.IPaymentRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpMethod

class PaymentRepository(
    private val httpClient: HttpClient
) : IPaymentRepository {
    override suspend fun startPayment(paymentRequest: PaymentRequest): RepositoryResponse<PaymentResponse> {
        val response = httpClient.safeRequest<PaymentResponse> {
            url("v1/transaction/startPaymentTransaction")
            method = HttpMethod.Post
            setBody(paymentRequest)
        }

        return when (response) {
            is RepositoryResponse.Error -> response
            is RepositoryResponse.Success -> RepositoryResponse.Success(response.body)
        }
    }
}
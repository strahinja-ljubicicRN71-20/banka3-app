package domain.repository

import data.model.payment.PaymentRequest
import data.model.payment.PaymentResponse
import domain.model.RepositoryResponse

interface IPaymentRepository {
    suspend fun startPayment(paymentRequest: PaymentRequest): RepositoryResponse<PaymentResponse>
}
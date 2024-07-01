package domain.usecase.payment

import data.model.payment.PaymentRequest
import data.model.payment.PaymentResponse
import domain.model.RepositoryResponse
import domain.repository.IPaymentRepository

class PaymentUseCase(
    private val repository: IPaymentRepository
) {
    suspend operator fun invoke(paymentRequest: PaymentRequest): PaymentResponse {
        return when (val result = repository.startPayment(paymentRequest)) {
            is RepositoryResponse.Success -> result.body
            is RepositoryResponse.Error -> PaymentResponse(
                transactionId = -1,
            )
        }
    }
}
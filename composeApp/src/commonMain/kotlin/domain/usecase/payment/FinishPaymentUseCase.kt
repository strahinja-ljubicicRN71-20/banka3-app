package domain.usecase.payment

import data.model.payment.ConfirmPaymentRequest
import domain.model.RepositoryResponse
import domain.repository.IEmailRepository

class FinishPaymentUseCase(
    private val repository: IEmailRepository
) {
    suspend operator fun invoke(transactionId: Long, code: Long): Boolean {
        return when (val response = repository.finishTransaction(ConfirmPaymentRequest(transactionId, code))) {
            is RepositoryResponse.Success -> response.body
            is RepositoryResponse.Error -> false
        }
    }
}
package domain.repository

import data.model.payment.ConfirmPaymentRequest
import domain.model.RepositoryResponse

interface IEmailRepository {
    suspend fun finishTransaction(confirmPaymentRequest: ConfirmPaymentRequest): RepositoryResponse<Boolean>
}
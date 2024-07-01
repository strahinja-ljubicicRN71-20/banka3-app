package ui.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.model.payment.PaymentRequest
import domain.model.payment.PaymentScreenInfo
import domain.usecase.payment.FinishPaymentUseCase
import domain.usecase.payment.PaymentUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PaymentViewModel(
    private val paymentUseCase: PaymentUseCase,
    private val finishPaymentUseCase: FinishPaymentUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), State())

    private val _showConfirmPaymentDialog = MutableSharedFlow<Long>()
    val showConfirmPaymentDialog = _showConfirmPaymentDialog.asSharedFlow()

    data class State(
        val recipientName: String = "",
        val recipientAccount: String = "",
        val referenceNumber: String = "",
        val paymentCode: String = "",
        val paymentPurpose: String = "",
        val amount: String = "",
        val confirmationCode: String = "",
    )

    fun setRecipientName(value: String) {
        _state.update { it.copy(recipientName = value) }
    }

    fun setRecipientAccount(value: String) {
        _state.update { it.copy(recipientAccount = value) }
    }

    fun setReferenceNumber(value: String) {
        _state.update { it.copy(referenceNumber = value) }
    }

    fun setPaymentCode(value: String) {
        _state.update { it.copy(paymentCode = value) }
    }

    fun setPaymentPurpose(value: String) {
        _state.update { it.copy(paymentPurpose = value) }
    }

    fun setAmount(value: String) {
        _state.update { it.copy(amount = value) }
    }

    fun setConfirmationCode(value: String) {
        if (value.isEmpty()) return
        _state.update { it.copy(confirmationCode = value) }
    }

    fun startPayment(paymentScreenInfo: PaymentScreenInfo) {
        viewModelScope.launch {
            val paymentRequest = PaymentRequest(
                accountFrom = paymentScreenInfo.account.accountNumber,
                accountTo = state.value.recipientAccount,
                amount = state.value.amount.toDouble(),
                currencyMark = paymentScreenInfo.account.currency.mark,
                sifraPlacanja = state.value.paymentCode.toInt(),
                pozivNaBroj = state.value.referenceNumber
            )
            val result = paymentUseCase(paymentRequest = paymentRequest)
            _showConfirmPaymentDialog.emit(result.transactionId)
        }
    }

    fun finishPayment(transactionId: Long) {
        viewModelScope.launch {
            finishPaymentUseCase(transactionId = transactionId, code = state.value.confirmationCode.toLong())
        }
    }
}
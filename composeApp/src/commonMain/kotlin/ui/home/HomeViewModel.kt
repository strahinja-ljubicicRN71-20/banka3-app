package ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.account.Account
import domain.model.transaction.Transaction
import domain.model.user.User
import domain.usecase.account.GetUserAccountsUseCase
import domain.usecase.transaction.GetAccountTransactionsUseCase
import domain.usecase.user.GetUserInfoUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getUserAccountsUseCase: GetUserAccountsUseCase,
    private val getAccountTransactionsUseCase: GetAccountTransactionsUseCase
) : ViewModel() {

    data class State(
        val firstName: String = "",
        val lastName: String = "",
        val userId: String = "",
        val rsdAccount: Account = Account(),
        val transactions: List<Transaction> = emptyList(),
        val isLoading: Boolean = false
    )

    private val _state = MutableStateFlow(State())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), State())

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            setIsLoading(true)
            val userInfo = getUserInfoUseCase()
            val rsdAccount = getUserAccountsUseCase(userInfo.id).find { it.currency.mark == "RSD" } ?: Account()
            val transactions = getAccountTransactionsUseCase(rsdAccount.accountNumber)
            delay(1000)
            updateState(user = userInfo, rsdAccount = rsdAccount, transactions = transactions)
        }
    }

    private fun updateState(user: User, rsdAccount: Account, transactions: List<Transaction>) {
        _state.update {
            it.copy(
                firstName = user.firstName,
                lastName = user.lastName,
                userId = user.id,
                rsdAccount = rsdAccount,
                transactions = transactions,
                isLoading = false
            )
        }
    }

    private fun setIsLoading(isLoading: Boolean) {
        _state.update { it.copy(isLoading = isLoading) }
    }
}
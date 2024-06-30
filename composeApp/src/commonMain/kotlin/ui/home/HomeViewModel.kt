package ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.account.Account
import domain.model.user.User
import domain.usecase.account.GetUserAccountsUseCase
import domain.usecase.user.GetUserInfoUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getUserAccountsUseCase: GetUserAccountsUseCase
) : ViewModel() {

    data class State(
        val firstName: String = "",
        val lastName: String = "",
        val userId: String = "",
        val accounts: List<Account> = emptyList(),
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
            val userInfo = getUserInfo()
            val accounts = getUserAccounts(userInfo.id)
            delay(1000)
            updateState(user = userInfo, accounts = accounts)
        }
    }

    private suspend fun getUserInfo(): User {
        return getUserInfoUseCase()
    }

    private suspend fun getUserAccounts(userId: String): List<Account> {
        return getUserAccountsUseCase(userId)
    }

    private fun updateState(user: User, accounts: List<Account>) {
        _state.update {
            it.copy(
                firstName = user.firstName,
                lastName = user.lastName,
                userId = user.id,
                accounts = accounts,
                isLoading = false
            )
        }
    }

    private fun setIsLoading(isLoading: Boolean) {
        _state.update { it.copy(isLoading = isLoading) }
    }
}
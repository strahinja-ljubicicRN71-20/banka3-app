package ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.login.IsUserActiveResult
import domain.model.login.LoginResult
import domain.usecase.login.IsUserActiveUseCase
import domain.usecase.login.LoginUseCase
import domain.usecase.user.StoreUserInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val isUserActive: IsUserActiveUseCase,
    private val storeUserInfo: StoreUserInfoUseCase,
    private val login: LoginUseCase
) : ViewModel() {

    data class State(
        val email: String = "",
        val password: String = "",
        val shouldShowPassword: Boolean = false,
        val loginButtonText: String = "Check email"
    )

    private val _nextScreen = Channel<Unit>()
    val nextScreen = _nextScreen.receiveAsFlow()

    private val _state = MutableStateFlow(State())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), State())

    fun setEmail(value: String) {
        _state.update { it.copy(email = value) }
    }

    fun setPassword(value: String) {
        _state.update { it.copy(password = value) }
    }

    fun handleLoginButtonClick() {
        viewModelScope.launch(Dispatchers.IO) {
            if (shouldShowPassword()) {
                login()
            } else {
                checkIfUserActive()
            }
        }
    }

    private fun email(): String = _state.value.email

    private fun password(): String = _state.value.password

    private fun shouldShowPassword(): Boolean = _state.value.shouldShowPassword

    private fun updateShowPassword() {
        _state.update {
            it.copy(
                shouldShowPassword = state.value.shouldShowPassword.not(),
                loginButtonText = if (state.value.shouldShowPassword) "Check email" else "Login"
            )
        }
    }

    private suspend fun checkIfUserActive() {
        val isUserActiveResult: IsUserActiveResult = isUserActive(email())
        if (isUserActiveResult.isSuccessful) {
            if (isUserActiveResult.codeActive) {
                updateShowPassword()
            }
        }
    }

    private suspend fun login() {
        val loggedIn: LoginResult = login(email(), password())
        if (loggedIn.isSuccessfulLogin) {
            val email = email()
            val user = storeUserInfo(email)
            if (user.id.isNotEmpty()) {
                _nextScreen.send(Unit)
            }
        }
    }
}
package ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.usecase.IsUserActiveUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val isUserActiveUseCase: IsUserActiveUseCase
) : ViewModel() {

    data class State(
        val email: String = "",
        val password: String = ""
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

    fun login() {

    }

    fun checkIfEmailActive() {
        viewModelScope.launch {
            isUserActiveUseCase(email())
        }
    }

    fun navigateToNextScreen() {
        viewModelScope.launch(Dispatchers.Default) {
            _nextScreen.send(Unit)
        }
    }

    private fun email(): String = _state.value.email
    private fun password(): String = _state.value.password
}
package login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    data class State(
        val email: String = "",
        val password: String = ""
    )

    private val _state = MutableStateFlow(State())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), State())

    fun setEmail(value: String) {
        _state.update { it.copy(email = value) }
    }

    fun setPassword(value: String) {
        _state.update { it.copy(password = value) }
    }
}
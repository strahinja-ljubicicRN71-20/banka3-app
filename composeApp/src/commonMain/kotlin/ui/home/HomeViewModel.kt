package ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.usecase.user.GetUserInfoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {

    data class State(
        val firstName: String = "",
    )

    private val _state = MutableStateFlow(State())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), State())

    fun getUserInfo() {
        viewModelScope.launch {
            val user = getUserInfoUseCase()
            _state.update { it.copy(firstName = user.firstName) }
        }
    }
}
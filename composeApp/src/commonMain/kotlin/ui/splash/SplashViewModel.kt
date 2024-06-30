package ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.usecase.splash.CheckIfUserAlreadyLoggedInUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val checkIfUserAlreadyLoggedInUseCase: CheckIfUserAlreadyLoggedInUseCase
) : ViewModel() {

    private val _nextScreen = Channel<NextScreen>()
    val nextScreen = _nextScreen.receiveAsFlow()

    fun checkIfUserLoggedIn() {
        viewModelScope.launch(Dispatchers.IO) {
            val isLoggedIn = checkIfUserAlreadyLoggedInUseCase()
            if (isLoggedIn) {
                _nextScreen.send(NextScreen.Main)
            } else {
                _nextScreen.send(NextScreen.Login)
            }
        }
    }
}

sealed interface NextScreen {
    data object Login : NextScreen
    data object Main : NextScreen
}
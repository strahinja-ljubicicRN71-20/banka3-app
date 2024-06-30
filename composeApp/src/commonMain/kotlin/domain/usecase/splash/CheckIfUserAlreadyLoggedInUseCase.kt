package domain.usecase.splash

import domain.repository.IUserPreference

class CheckIfUserAlreadyLoggedInUseCase(
    private val preferences: IUserPreference
) {
    suspend operator fun invoke(): Boolean {
        val token = preferences.getToken()

        return token != ""
    }
}
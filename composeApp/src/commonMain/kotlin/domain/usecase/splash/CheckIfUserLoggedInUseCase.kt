package domain.usecase.splash

import domain.repository.IUserPreference

class CheckIfUserLoggedInUseCase(
    private val preferences: IUserPreference
) {
    suspend operator fun invoke(): Boolean {
        val token = preferences.getToken()

        return token != ""
    }
}
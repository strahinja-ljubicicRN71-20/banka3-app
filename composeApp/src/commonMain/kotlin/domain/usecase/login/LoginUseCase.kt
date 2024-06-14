package domain.usecase.login

import domain.model.RepositoryResponse
import domain.model.login.LoginResult
import domain.repository.ILoginRepository
import domain.repository.IUserPreference

class LoginUseCase(
    private val repository: ILoginRepository,
    private val preferences: IUserPreference
) {

    suspend operator fun invoke(email: String, password: String): LoginResult {
        return when (val result = repository.login(email, password)) {
            is RepositoryResponse.Success -> {
                preferences.storeToken(result.body.token)
                LoginResult(
                    isSuccessfulLogin = true
                )
            }

            is RepositoryResponse.Error -> {
                LoginResult(
                    isSuccessfulLogin = false
                )
            }
        }
    }
}
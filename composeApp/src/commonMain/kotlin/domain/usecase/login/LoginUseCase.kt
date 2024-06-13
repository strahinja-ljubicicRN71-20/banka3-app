package domain.usecase.login

import domain.model.RepositoryResponse
import domain.model.login.LoginResult
import domain.repository.ILoginRepository

class LoginUseCase(
    private val repository: ILoginRepository
) {

    suspend operator fun invoke(email: String, password: String): LoginResult {
        return when (val result = repository.login(email, password)) {
            is RepositoryResponse.Success -> {
                //TODO: Store token into preference
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
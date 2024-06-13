package domain.usecase.login

import domain.model.login.IsUserActiveResult
import domain.model.RepositoryResponse
import domain.repository.ILoginRepository

class IsUserActiveUseCase(
    private val repository: ILoginRepository
) {
    suspend operator fun invoke(email: String): IsUserActiveResult {
        return when (val response = repository.isUserActive(email = email)) {
            is RepositoryResponse.Success -> {
                IsUserActiveResult(
                    isSuccessful = true,
                    email = response.body.email,
                    codeActive = response.body.codeActive
                )
            }

            is RepositoryResponse.Error -> {
                IsUserActiveResult(
                    isSuccessful = false
                )
            }
        }
    }
}
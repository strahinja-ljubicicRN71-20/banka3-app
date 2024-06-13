package domain.usecase

import domain.model.IsUserActiveResult
import domain.repository.ILoginRepository

class IsUserActiveUseCase(
    val repository: ILoginRepository
) {

    suspend operator fun invoke(email: String): IsUserActiveResult {
        repository.isUserActive(email = email)
        return IsUserActiveResult()
    }
}
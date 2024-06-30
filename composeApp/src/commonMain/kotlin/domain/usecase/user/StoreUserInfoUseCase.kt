package domain.usecase.user

import domain.model.RepositoryResponse
import domain.model.user.User
import domain.repository.IUserPreference
import domain.repository.IUserRepository

class StoreUserInfoUseCase(
    private val repository: IUserRepository,
    private val preference: IUserPreference
) {
    suspend operator fun invoke(email: String): User {
        return when (val result = repository.fetchUserInfo(email)) {
            is RepositoryResponse.Error -> User()
            is RepositoryResponse.Success -> {
                preference.storeUserData(result.body)
                return result.body
            }
        }
    }
}
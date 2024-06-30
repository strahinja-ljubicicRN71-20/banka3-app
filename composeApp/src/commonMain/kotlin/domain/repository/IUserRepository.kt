package domain.repository

import domain.model.RepositoryResponse
import domain.model.user.User

interface IUserRepository {
    suspend fun fetchUserInfo(email: String): RepositoryResponse<User>
}
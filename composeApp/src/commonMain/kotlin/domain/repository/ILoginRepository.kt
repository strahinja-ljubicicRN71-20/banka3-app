package domain.repository

import data.model.login.IsUserActiveResponse
import data.model.login.LoginResponse
import domain.model.RepositoryResponse

interface ILoginRepository {
    suspend fun isUserActive(email: String): RepositoryResponse<IsUserActiveResponse>
    suspend fun login(email: String, password: String): RepositoryResponse<LoginResponse>
}
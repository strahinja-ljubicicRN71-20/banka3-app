package data.repository.login.remote

import data.model.login.IsUserActiveResponse
import data.model.login.LoginRequest
import data.model.login.LoginResponse
import data.util.safeRequest
import domain.model.RepositoryResponse
import domain.repository.ILoginRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpMethod

class LoginRepository(
    private val httpClient: HttpClient
) : ILoginRepository {
    override suspend fun isUserActive(email: String): RepositoryResponse<IsUserActiveResponse> {
        return httpClient.safeRequest {
            url("v1/user/isUserActive/$email")
            method = HttpMethod.Get
        }
    }

    override suspend fun login(email: String, password: String): RepositoryResponse<LoginResponse> {
        return httpClient.safeRequest {
            url("v1/user/auth/login")
            method = HttpMethod.Post
            setBody(LoginRequest(email, password))
        }
    }
}

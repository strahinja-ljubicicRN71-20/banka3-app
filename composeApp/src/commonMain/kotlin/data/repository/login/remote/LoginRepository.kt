package data.repository.login.remote

import domain.repository.ILoginRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.request
import io.ktor.http.HttpMethod

class LoginRepository(
    private val httpClient: HttpClient
) : ILoginRepository {
    override suspend fun isUserActive(email: String) {
        httpClient.request("v1/user/isUserActive/$email") {
            method = HttpMethod.Get
        }
    }
}
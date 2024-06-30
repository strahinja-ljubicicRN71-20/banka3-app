package data.repository.user.remote

import data.model.user.UserInfoDto
import data.repository.user.UserInfoMapper
import data.util.safeRequest
import domain.model.RepositoryResponse
import domain.model.user.User
import domain.repository.IUserRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.url
import io.ktor.http.HttpMethod

class UserRepository(
    private val httpClient: HttpClient,
    private val userInfoMapper: UserInfoMapper
) : IUserRepository {
    override suspend fun fetchUserInfo(email: String): RepositoryResponse<User> {
        val response = httpClient.safeRequest<UserInfoDto> {
            url("v1/user/findByEmail/$email")
            method = HttpMethod.Get
        }

        return when (response) {
            is RepositoryResponse.Error -> response
            is RepositoryResponse.Success -> RepositoryResponse.Success(userInfoMapper.map(response.body))
        }
    }
}
package data.repository.account.remote

import data.model.account.AccountDto
import data.repository.account.AccountMapper
import data.util.safeRequest
import domain.model.RepositoryResponse
import domain.model.account.Account
import domain.repository.IAccountRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.url
import io.ktor.http.HttpMethod

class AccountRepository(
    private val httpClient: HttpClient,
    private val accountMapper: AccountMapper
) : IAccountRepository {
    override suspend fun getUserAccounts(userId: String): RepositoryResponse<List<Account>> {
        val response = httpClient.safeRequest<List<AccountDto>> {
            url("v1/account/getByUser/$userId")
            method = HttpMethod.Get
        }

        return when (response) {
            is RepositoryResponse.Error -> response
            is RepositoryResponse.Success -> RepositoryResponse.Success(accountMapper.map(response.body))
        }
    }

}
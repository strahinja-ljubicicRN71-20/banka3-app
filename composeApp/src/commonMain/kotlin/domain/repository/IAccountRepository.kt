package domain.repository

import domain.model.RepositoryResponse
import domain.model.account.Account

interface IAccountRepository {
    suspend fun getUserAccounts(userId: String): RepositoryResponse<List<Account>>
}
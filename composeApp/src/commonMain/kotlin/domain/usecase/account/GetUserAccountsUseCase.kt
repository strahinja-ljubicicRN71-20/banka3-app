package domain.usecase.account

import domain.model.RepositoryResponse
import domain.model.account.Account
import domain.repository.IAccountRepository

class GetUserAccountsUseCase(
    private val repository: IAccountRepository
) {

    suspend operator fun invoke(userId: String) : List<Account> {
        return when(val response = repository.getUserAccounts(userId)) {
            is RepositoryResponse.Error -> emptyList()
            is RepositoryResponse.Success -> response.body
        }
    }
}
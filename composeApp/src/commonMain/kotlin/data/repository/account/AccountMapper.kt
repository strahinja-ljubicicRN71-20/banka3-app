package data.repository.account

import data.model.account.AccountDto
import domain.model.account.Account
import domain.model.account.currency.Currency

class AccountMapper {
    fun map(value: List<AccountDto>): List<Account> {
        return value.map {
            Account(
                accountId = it.accountId,
                userId = it.userId,
                employeeId = it.employeeId,
                accountNumber = it.accountNumber,
                availableBalance = it.availableBalance,
                reservedAmount = it.reservedAmount,
                creationDate = it.creationDate,
                expireDate = it.expireDate,
                active = it.active,
                currency = Currency(
                    currencyId = it.currency.currencyId,
                    name = it.currency.name,
                    mark = it.currency.mark
                ),
                accountType = it.accountType
            )
        }
    }
}
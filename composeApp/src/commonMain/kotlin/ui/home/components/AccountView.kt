package ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.model.account.Account

@Composable
fun AccountView(
    accounts: List<Account>
) {
    val rsdAccount = accounts.find { it.currency.mark == "RSD" }

    if (rsdAccount != null) {
        AccountItem(account = rsdAccount)
    }
}

@Composable
fun AccountItem(account: Account) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = account.currency.mark)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = account.availableBalance.toString())
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = account.reservedAmount.toString())
    }
    Column(
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surfaceContainerHigh)
    ) {

    }
}
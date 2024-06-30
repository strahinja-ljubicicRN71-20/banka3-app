package ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import banka3_app.composeapp.generated.resources.Res
import banka3_app.composeapp.generated.resources.transactions_icon
import domain.model.account.Account
import domain.model.transaction.Transaction
import org.jetbrains.compose.resources.painterResource
import ui.home.components.AccountView
import ui.home.components.TransactionItem
import utils.collectAsStateMultiplatform
import utils.koinViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinViewModel()
) {

    val state by homeViewModel.state.collectAsStateMultiplatform()

    val list = mutableListOf<Transaction>().apply {
        repeat(10) {
            add(
                Transaction(
                    accountFrom = "Account $it",
                    accountTo = "Account $it",
                    amount = 100.0,
                    sifraPlacanja = 123,
                    pozivNaBroj = "1233",
                    date = 123412341234
                )
            )
        }
    }

    HomeScreenContent(
        firstName = state.firstName,
        lastName = state.lastName,
        account = state.rsdAccount,
        transactions = state.transactions,
        isLoading = state.isLoading
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenContent(
    firstName: String = "",
    lastName: String = "",
    account: Account? = null,
    transactions: List<Transaction> = emptyList(),
    isLoading: Boolean = false
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        } else {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "Welcome, ",
                                    fontSize = 24.sp
                                )
                                Text(
                                    text = "$firstName $lastName",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp
                                )
                            }
                        },
                        colors = TopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                            scrolledContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                            titleContentColor = MaterialTheme.colorScheme.onSurface,
                            actionIconContentColor = MaterialTheme.colorScheme.onSurface
                        )
                    )
                },
                content = {
                    Column(
                        modifier = Modifier.padding(
                            PaddingValues(
                                top = it.calculateTopPadding() + 16.dp,
                                bottom = 80.dp
                            )
                        ),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AccountView(account = account ?: Account())
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(PaddingValues(start = 16.dp, top = 32.dp))
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.transactions_icon),
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Transactions",
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        LazyColumn {
                            itemsIndexed(items = transactions, key = { index, _ -> index }) { index, transaction ->
                                TransactionItem(
                                    transaction = transaction,
                                    currencyMark = account?.currency?.mark ?: "",
                                    myAccount = account?.accountNumber ?: "",
                                    isLastItem = index == transactions.size - 1
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}
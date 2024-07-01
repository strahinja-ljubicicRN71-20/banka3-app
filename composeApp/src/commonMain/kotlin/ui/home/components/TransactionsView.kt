package ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun TransactionsView(
    transactions: List<Transaction>,
    account: Account?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(start = 16.dp, top = 16.dp)),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
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
    Spacer(modifier = Modifier.height(8.dp))
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
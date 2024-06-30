package ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.model.account.Account

@Composable
fun AccountView(account: Account) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
        ),
        modifier = Modifier.fillMaxWidth().height(200.dp)
            .padding(paddingValues = PaddingValues(start = 16.dp, end = 16.dp))
    ) {
        Text(
            modifier = Modifier.padding(PaddingValues(start = 16.dp, top = 16.dp)),
            text = account.accountNumber,
            fontSize = 16.sp,
            color = Color.DarkGray
        )
        Column(
            modifier = Modifier.fillMaxWidth().padding(PaddingValues(start = 16.dp))
        ) {
            Text(
                modifier = Modifier.padding(PaddingValues(start = 16.dp, top = 16.dp)),
                text = "Available balance",
                fontSize = 16.sp,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.padding(PaddingValues(start = 32.dp)),
                text = "${account.currency.mark}: ${account.availableBalance}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(PaddingValues(start = 16.dp, top = 16.dp)),
                text = "Reserved amount",
                fontSize = 16.sp,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.padding(PaddingValues(start = 32.dp)),
                text = "${account.currency.mark}: ${account.reservedAmount}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
package ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import banka3_app.composeapp.generated.resources.Res
import banka3_app.composeapp.generated.resources.minus_icon
import banka3_app.composeapp.generated.resources.plus_icon
import domain.model.transaction.Transaction
import org.jetbrains.compose.resources.painterResource

@Composable
fun TransactionItem(
    transaction: Transaction,
    currencyMark: String,
    myAccount: String,
    isLastItem: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(80.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxHeight().weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "01",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "JUL",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray
            )
        }
        Column(
            modifier = Modifier.fillMaxHeight().weight(2f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = transaction.accountFrom,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$currencyMark ${transaction.amount}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        Column(
            modifier = Modifier.fillMaxHeight().weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (myAccount == transaction.accountTo) {
                Icon(
                    painter = painterResource(Res.drawable.plus_icon),
                    contentDescription = null,
                )
            } else {
                Icon(
                    painter = painterResource(Res.drawable.minus_icon),
                    contentDescription = null,
                )
            }
        }
    }
    if (!isLastItem) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .height(2.dp)
                .padding(PaddingValues(start = 20.dp, end = 20.dp))
                .background(color = Color.Gray)
        )
    }
}
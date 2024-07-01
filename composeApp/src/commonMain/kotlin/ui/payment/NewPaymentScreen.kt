package ui.payment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import domain.model.payment.PaymentScreenInfo
import navigation.MainDestinations
import utils.collectAsStateMultiplatform
import utils.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPaymentScreen(
    navController: NavController,
    paymentViewModel: PaymentViewModel = koinViewModel(),
    paymentScreenInfo: PaymentScreenInfo
) {

    val state by paymentViewModel.state.collectAsStateMultiplatform()
    var showDialog by remember { mutableStateOf(false) }
    var transactionId by remember { mutableStateOf(-1L) }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = Unit) {
        paymentViewModel.showConfirmPaymentDialog.collect { id ->
            showDialog = true
            transactionId = id
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        },
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                    )
                },
                title = {
                    Text("New payment")
                },
            )
        }
    ) {
        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                },
                title = {
                    Text(text = "Confirm transaction")
                },
                text = {
                    Column {
                        Text(text = "Enter confirmation code:")
                        TextField(
                            value = state.confirmationCode,
                            onValueChange = paymentViewModel::setConfirmationCode,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        paymentViewModel.finishPayment(transactionId)
                        showDialog = false
                        navController.navigate(MainDestinations.HomeScreen.path) {
                            popUpTo(MainDestinations.HomeScreen.path) {
                                inclusive = true
                            }
                        }
                    }) {
                        Text("Confirm")
                    }
                },
            )
        } else {
            NewPaymentScreenContent(
                recipientName = state.recipientName,
                recipientAccount = state.recipientAccount,
                referenceNumber = state.referenceNumber,
                paymentCode = state.paymentCode,
                paymentPurpose = state.paymentPurpose,
                amount = state.amount,
                onRecipientNameChange = paymentViewModel::setRecipientName,
                onRecipientAccountChange = paymentViewModel::setRecipientAccount,
                onReferenceNumberChange = paymentViewModel::setReferenceNumber,
                onPaymentCodeChange = paymentViewModel::setPaymentCode,
                onPaymentPurposeChange = paymentViewModel::setPaymentPurpose,
                onAmountChange = paymentViewModel::setAmount,
                startPayment = paymentViewModel::startPayment,
                paymentScreenInfo = paymentScreenInfo,
                focusManager = focusManager,
            )
        }
    }

}

@Composable
fun NewPaymentScreenContent(
    recipientName: String = "",
    recipientAccount: String = "",
    referenceNumber: String = "",
    paymentCode: String = "",
    paymentPurpose: String = "",
    amount: String = "",
    onRecipientNameChange: (String) -> Unit,
    onRecipientAccountChange: (String) -> Unit,
    onReferenceNumberChange: (String) -> Unit,
    onPaymentCodeChange: (String) -> Unit,
    onPaymentPurposeChange: (String) -> Unit,
    onAmountChange: (String) -> Unit,
    startPayment: (PaymentScreenInfo) -> Unit = {},
    paymentScreenInfo: PaymentScreenInfo,
    focusManager: FocusManager
) {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = { focusManager.clearFocus() }
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Enter payment details",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = recipientName,
            onValueChange = { onRecipientNameChange(it) },
            label = { Text("Naziv primaoca") },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = recipientAccount,
            onValueChange = { onRecipientAccountChange(it) },
            label = { Text("Račun primaoca") },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = referenceNumber,
            onValueChange = { onReferenceNumberChange(it) },
            label = { Text("Poziv na broj") },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = paymentCode,
            onValueChange = { onPaymentCodeChange(it) },
            label = { Text("Šifra plaćanja") },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = paymentPurpose,
            onValueChange = { onPaymentPurposeChange(it) },
            label = { Text("Svrha plaćanja") },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = amount,
            onValueChange = { onAmountChange(it) },
            label = { Text("Iznos") },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                startPayment(paymentScreenInfo)
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Submit")
        }
    }
}
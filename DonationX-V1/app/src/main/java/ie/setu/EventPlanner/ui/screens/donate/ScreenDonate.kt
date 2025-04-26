package ie.setu.EventPlanner.ui.screens.donate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.setValue
import ie.setu.EventPlanner.data.EventModel
import ie.setu.EventPlanner.ui.components.donate.AmountPicker
import ie.setu.EventPlanner.ui.components.donate.DonateButton
import ie.setu.EventPlanner.ui.components.donate.MessageInput
import ie.setu.EventPlanner.ui.components.donate.ProgressBar
import ie.setu.EventPlanner.ui.components.donate.RadioButtonGroup
import ie.setu.EventPlanner.ui.components.donate.WelcomeText
import ie.setu.EventPlanner.ui.screens.report.ReportViewModel
import ie.setu.EventPlanner.ui.theme.EventPlannerTheme

@Composable
fun ScreenDonate(
    modifier: Modifier = Modifier,
    reportViewModel: ReportViewModel = hiltViewModel()
) {
    // Collect the donations from the ViewModel
    val donations by reportViewModel.uiDonations.collectAsState(initial = emptyList())

    var paymentType by remember { mutableStateOf("Paypal") }
    var paymentAmount by remember { mutableIntStateOf(10) }
    var paymentMessage by remember { mutableStateOf("Go Homer!") }
    var totalDonated by remember { mutableIntStateOf(0) }

    // Calculate the total amount donated so far
    totalDonated = donations.sumOf { it.paymentAmount }

    Column {
        Column(
            modifier = modifier.padding(
                start = 24.dp,
                end = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(30.dp),
        ) {
            WelcomeText()
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Payment type selector (RadioButtonGroup)
                RadioButtonGroup(
                    modifier = modifier,
                    onPaymentTypeChange = { paymentType = it }
                )
                Spacer(modifier.weight(1f))
                // Amount picker (AmountPicker)
                AmountPicker(
                    onPaymentAmountChange = { paymentAmount = it }
                )
            }
            // Progress bar showing the total amount donated
            ProgressBar(
                modifier = modifier,
                totalDonated = totalDonated
            )
            // Input for donation message
            MessageInput(
                modifier = modifier,
                onMessageChange = { paymentMessage = it }
            )
            // Donate button that adds a donation
            DonateButton(
                modifier = modifier,
                donation = EventModel(
                    paymentType = paymentType,
                    paymentAmount = paymentAmount,
                    message = paymentMessage
                ),
                onTotalDonatedChange = { totalDonated = it }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DonateScreenPreview() {
    EventPlannerTheme {
        // Preview of the Donate screen with some fake donations
        ScreenDonate(
            modifier = Modifier,
            reportViewModel = hiltViewModel()
        )
    }
}

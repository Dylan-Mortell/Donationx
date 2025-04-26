package ie.setu.EventPlanner.ui.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ie.setu.EventPlanner.ui.components.details.DetailsScreenText
import ie.setu.EventPlanner.ui.components.details.ReadOnlyTextField
import androidx.compose.material3.MaterialTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    detailViewModel: DetailsViewModel = hiltViewModel()
) {
    var donation = detailViewModel.donation.value

    // Move the state variables above where they are used
    var text by rememberSaveable { mutableStateOf(donation.message) }
    var onMessageChanged by rememberSaveable { mutableStateOf(false) }
    var isEmptyError by rememberSaveable { mutableStateOf(false) }
    var isShortError by rememberSaveable { mutableStateOf(false) }

    // Error messages
    val errorEmptyMessage = "Message Cannot be Empty..."
    val errorShortMessage = "Message must be at least 2 characters"

    // Function to validate the message
    fun validate(text: String) {
        isEmptyError = text.isEmpty()
        isShortError = text.length < 2
        onMessageChanged = !(isEmptyError || isShortError)
    }

    // Column layout for the screen
    Column(
        modifier = modifier.padding(start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        DetailsScreenText()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(start = 10.dp, end = 10.dp),
        ) {
            // Payment Type Field
            ReadOnlyTextField(value = donation.paymentType, label = "Payment Type")

            // Payment Amount Field
            ReadOnlyTextField(value = "€" + donation.paymentAmount.toString(), label = "Payment Amount")

            // Date Donated Field
            ReadOnlyTextField(value = donation.dateDonated.toString(), label = "Date Donated")

            // Message Field
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = {
                    text = it
                    validate(text)
                    donation.message = text
                },
                maxLines = 2,
                label = { Text(text = "Message") },
                isError = isEmptyError || isShortError,
                supportingText = {
                    if (isEmptyError) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = errorEmptyMessage,
                            color = MaterialTheme.colorScheme.error
                        )
                    } else if (isShortError) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = errorShortMessage,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                trailingIcon = {
                    if (isEmptyError || isShortError)
                        Icon(Icons.Filled.Warning, "error", tint = MaterialTheme.colorScheme.error)
                    else
                        Icon(
                            Icons.Default.Edit, contentDescription = "add/edit",
                            tint = Color.Black
                        )
                },
                keyboardActions = KeyboardActions { validate(text) },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                )
            )

            // End of Message Field
            Spacer(modifier = Modifier.height(48.dp))

            // Save Button
            Button(
                onClick = {
                    detailViewModel.updateDonation(donation)
                    onMessageChanged = false
                },
                elevation = ButtonDefaults.elevation(defaultElevation = 20.dp),
                enabled = onMessageChanged
            ) {
                Icon(Icons.Default.Save, contentDescription = "Save")
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Save",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
    }
}


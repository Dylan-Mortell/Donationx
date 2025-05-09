package ie.setu.EventPlanner

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ie.setu.EventPlanner.ui.theme.EventPlannerTheme

class InvitationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventPlannerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InvitationScreen()
                }
            }
        }
    }
}

@Composable
fun InvitationScreen() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Send Invitation",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Invitee Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone Number (Optional)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (email.isBlank()) {
                    Toast.makeText(
                        it.context,
                        "Please enter an email.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        it.context,
                        "Invitation sent to $email!",
                        Toast.LENGTH_SHORT
                    ).show()
                    // TODO: Add actual send logic here
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Send Invitation")
        }
    }
}

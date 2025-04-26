package ie.setu.EventPlanner.ui.components.details

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ie.setu.EventPlanner.ui.theme.EventPlannerTheme

@Composable
fun ReadOnlyTextField(value: String, label: String) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = {},
        label = { Text(text = label) },
        readOnly = true,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = androidx.compose.material3.MaterialTheme.colorScheme.secondary
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ReadOnlyTextFieldPreview() {
    EventPlannerTheme {
        ReadOnlyTextField("My Message", "My Title")
    }
}

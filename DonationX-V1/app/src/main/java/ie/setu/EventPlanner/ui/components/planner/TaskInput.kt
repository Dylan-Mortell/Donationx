package ie.setu.EventPlanner.ui.components.planner

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ie.setu.EventPlanner.ui.theme.EventPlannerTheme
import ie.setu.donationx.R  // Make sure your string resource is here

@Composable
fun TaskInput(
    modifier: Modifier = Modifier,
    onTaskChange: (String) -> Unit
) {
    var task by remember { mutableStateOf("") }

    OutlinedTextField(
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
        ),
        maxLines = 1,
        value = task,
        onValueChange = {
            task = it
            onTaskChange(task)
        },
        modifier = modifier.fillMaxWidth(),
        label = { Text(stringResource(R.string.enter_task)) }
    )
}

@Preview(showBackground = true)
@Composable
fun TaskInputPreview() {
    EventPlannerTheme {
        TaskInput(
            Modifier,
            onTaskChange = {}
        )
    }
}

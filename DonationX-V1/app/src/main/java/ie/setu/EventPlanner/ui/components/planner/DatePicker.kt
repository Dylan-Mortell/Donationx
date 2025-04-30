package ie.setu.EventPlanner.ui.components.planner

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import java.time.LocalDate
import java.util.*
import ie.setu.EventPlanner.ui.theme.EventPlannerTheme

@Composable
fun DatePicker(
    onDateSelected: (LocalDate) -> Unit
) {
    val context = LocalContext.current
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val date = LocalDate.of(year, month + 1, dayOfMonth)
            selectedDate = date
            onDateSelected(date)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Button(
        onClick = { datePickerDialog.show() },
        colors = MaterialTheme.colorScheme.let {
            ButtonDefaults.buttonColors(containerColor = it.primary)
        }
    ) {
        Text(
            text = selectedDate?.toString() ?: "Select Date",
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DatePickerPreview() {
    EventPlannerTheme {
        DatePicker(onDateSelected = {})
    }
}

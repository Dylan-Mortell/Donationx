package ie.setu.EventPlanner.ui.screens.calendar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ie.setu.EventPlanner.ui.components.calendar.CalendarHeader
import ie.setu.EventPlanner.ui.components.details.ReadOnlyTextField
import java.time.LocalDate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
    calendarViewModel: CalendarViewModel = hiltViewModel()
) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var noteText by rememberSaveable { mutableStateOf("") }
    var onNoteChanged by rememberSaveable { mutableStateOf(false) }
    var isEmptyError by rememberSaveable { mutableStateOf(false) }

    val errorEmptyNote = "Note cannot be empty."

    fun validate(text: String) {
        isEmptyError = text.isEmpty()
        onNoteChanged = !isEmptyError
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        CalendarHeader(date = selectedDate, onDateChange = { selectedDate = it })

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp)
        ) {
            ReadOnlyTextField(
                value = selectedDate.toString(),
                label = "Selected Date"
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = noteText,
                onValueChange = {
                    noteText = it
                    validate(noteText)
                    calendarViewModel.updateNoteForDate(selectedDate, noteText)
                },
                maxLines = 3,
                label = { Text("Note") },
                isError = isEmptyError,
                supportingText = {
                    if (isEmptyError) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = errorEmptyNote,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                trailingIcon = {
                    if (isEmptyError)
                        Icon(Icons.Filled.Warning, contentDescription = "error", tint = MaterialTheme.colorScheme.error)
                    else
                        Icon(Icons.Default.CalendarToday, contentDescription = "calendar")
                },
                keyboardActions = KeyboardActions { validate(noteText) },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                )
            )

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = {
                    calendarViewModel.saveNoteForDate(selectedDate, noteText)
                    onNoteChanged = false
                },
                elevation = ButtonDefaults.elevation(defaultElevation = 20.dp),
                enabled = onNoteChanged
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

package ie.setu.EventPlanner.ui.screens.planner

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ie.setu.EventPlanner.ui.components.planner.*
import ie.setu.EventPlanner.ui.theme.EventPlannerTheme
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date


fun LocalDate.toDate(): Date = Date.from(this.atStartOfDay(ZoneId.systemDefault()).toInstant())

@Composable
fun ScreenPlanner(
    modifier: Modifier = Modifier,
    viewModel: PlannerViewModel = hiltViewModel()
) {
    var task by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var completedTasks by remember { mutableIntStateOf(0) }
    val totalTasks = 5

    Column {
        Column(
            modifier = modifier.padding(start = 24.dp, end = 24.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            PlannerWelcome()

            TaskInput(
                modifier = modifier,
                onTaskChange = { task = it }
            )

            DatePicker(
                onDateSelected = { date -> selectedDate = date }
            )

            ProgressIndicator(
                modifier = modifier,
                completedTasks = completedTasks,
                totalTasks = totalTasks
            )

            SavePlanButton(
                modifier = modifier,
                onClick = {
                    viewModel.task.value = task
                    viewModel.date.value = selectedDate.toDate()
                    viewModel.insertPlannerItem()
                    completedTasks += 1
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlannerScreenPreview() {
    EventPlannerTheme {
        ScreenPlanner()
    }
}

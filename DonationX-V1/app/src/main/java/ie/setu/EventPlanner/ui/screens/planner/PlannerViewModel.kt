package ie.setu.EventPlanner.ui.screens.planner

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.EventPlanner.data.EventModel
import ie.setu.EventPlanner.data.PlannerModel
import ie.setu.EventPlanner.data.firebase.services.FirestoreService
import ie.setu.EventPlanner.data.firebase.services.AuthService
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import java.util.Date

@HiltViewModel
class PlannerViewModel @Inject constructor(
    private val firestore: FirestoreService,
    private val authService: AuthService
) : ViewModel() {

    var isLoading = mutableStateOf(false)
    var isErr = mutableStateOf(false)
    var error = mutableStateOf<Exception?>(null)

    var task = mutableStateOf("")
    var date = mutableStateOf(Date())

    fun insertPlannerItem() = viewModelScope.launch {
        try {
            isLoading.value = true
            val plannerItem = PlannerModel(
                title = task.value,
                date = date.value,
                isCompleted = false
            )
            firestore.insertPlanner(plannerItem)
            isErr.value = false
        } catch (e: Exception) {
            isErr.value = true
            error.value = e
        } finally {
            isLoading.value = false
        }
    }
}


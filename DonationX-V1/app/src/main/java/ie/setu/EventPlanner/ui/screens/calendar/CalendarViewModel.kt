package ie.setu.EventPlanner.ui.screens.calendar

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.EventPlanner.data.EventModel
import ie.setu.EventPlanner.data.firebase.services.FirestoreService
import ie.setu.EventPlanner.services.AuthService
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import java.time.LocalDate

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val repository: FirestoreService,
    private val authService: AuthService,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var calendarEvent = mutableStateOf(EventModel())
    private val date: String = checkNotNull(savedStateHandle["date"]) // passed as yyyy-MM-dd string

    init {
        viewModelScope.launch {
            val event = repository.getEventByDate(authService.email!!, date)
            if (event != null) {
                calendarEvent.value = event
            } else {
                calendarEvent.value = EventModel(date = date)
            }
        }
    }

    fun updateNoteForDate(date: LocalDate, note: String) {
        calendarEvent.value = calendarEvent.value.copy(date = date.toString(), message = note)
    }

    fun saveNoteForDate(date: LocalDate, note: String) {
        viewModelScope.launch {
            val updatedEvent = calendarEvent.value.copy(date = date.toString(), message = note)
            repository.update(updatedEvent)
        }
    }
}

package ie.setu.EventPlanner.ui.screens.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.EventPlanner.data.EventModel
import ie.setu.EventPlanner.data.Repository.RoomRepository
import ie.setu.EventPlanner.data.firebase.services.FirestoreService
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class DetailsViewModel @Inject
constructor(private val repository: FirestoreService,
            private val authService: AuthService,
            savedStateHandle: SavedStateHandle
) : ViewModel() {

    var donation = mutableStateOf(EventModel())
    val id: Int = checkNotNull(savedStateHandle["id"])

    init {
        viewModelScope.launch {
            repository.get(id).collect { objDonation ->
                donation.value = repository.get(authService.email!!,id)!!
            }
        }
    }

    fun updateDonation(donation: EventModel) {
        viewModelScope.launch { repository.update(donation) }
    }
}

package ie.setu.EventPlanner.ui.screens.donate

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.EventPlanner.data.EventModel
import ie.setu.EventPlanner.data.Repository.RoomRepository
import ie.setu.EventPlanner.data.api.RetrofitRepository
import ie.setu.EventPlanner.data.firebase.services.FirestoreService
import jakarta.inject.Inject
import kotlinx.coroutines.launch

class DonateViewModel @Inject
constructor(private val repository: FirestoreService,
            private val authService: AuthService)
    : ViewModel() {
    var isErr = mutableStateOf(false)
    var error = mutableStateOf(Exception())
    var isLoading = mutableStateOf(false)

//    fun insert(donation: DonationModel)
//            = viewModelScope.launch {
//                repository.insert(donation)
//    }


    fun insert(donation: EventModel) =
        viewModelScope.launch {
            try {
                isLoading.value = true
                repository.insert(donation)
                isErr.value = false
                isLoading.value = false
            } catch (e: Exception) {
                isErr.value = true
                error.value = e
                isLoading.value = false
            }
        }
}



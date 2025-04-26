package ie.setu.EventPlanner.ui.screens.report

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.EventPlanner.data.EventModel
import ie.setu.EventPlanner.data.api.RetrofitRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class ReportViewModel @Inject
constructor(private val repository: RetrofitRepository, private val authService: AuthService
) : ViewModel() {
    private val _donations = MutableStateFlow<List<EventModel>>(emptyList())
    val uiDonations: StateFlow<List<EventModel>> = _donations.asStateFlow()
    var isErr = mutableStateOf(false)
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf(Exception())

    init {
        viewModelScope.launch {
            repository.getAll(authService.email!!).collect { listOfDonations ->
                _donations.value = listOfDonations
            }
        }
    }

    init {
        getDonations()
    }

    fun getDonations() {
        viewModelScope.launch {
            try {
                isloading.value = true
                repository.getAll(authService.email!!).collect { items ->
                    _donations.value = items
                    iserror.value = false
                    isloading.value = false
                }
                Timber.i("DVM RVM = : ${_donations.value}")
            } catch (e: Exception) {
                iserror.value = true
                isloading.value = false
                error.value = e
                Timber.i("RVM Error ${e.message}")
            }
        }
    }


    fun deleteDonation(donation: EventModel)
            = viewModelScope.launch {
        repository.delete(authService.email!!,donation._id)
    }
}


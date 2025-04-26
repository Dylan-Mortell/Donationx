package ie.setu.EventPlanner.data.firebase.services

import ie.setu.EventPlanner.data.EventModel
import kotlinx.coroutines.flow.Flow

typealias Donation = EventModel
typealias Donations = Flow<List<Donation>>

interface FirestoreService {

    suspend fun getAll(email: String) : Donations
    suspend fun get(email: String, donationId: String) : Donation?
    suspend fun insert(email: String, donation: Donation)
    suspend fun update(email: String, donation: Donation)
    suspend fun delete(email: String, donationId: String)
}

package ie.setu.EventPlanner.data.Repository

import ie.setu.EventPlanner.data.EventModel
import ie.setu.EventPlanner.data.api.DonationService
import ie.setu.EventPlanner.data.api.DonationWrapper
import ie.setu.EventPlanner.data.room.DonationDAO
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RetrofitRepository @Inject
constructor(private val serviceApi: DonationService)  {

    suspend fun getAll(email: String): List<EventModel>
    {
        return withContext(Dispatchers.IO) {
            val donations = serviceApi.getall(email)
            donations.body() ?: emptyList()
        }
    }

    suspend fun get(email: String,
                    id: String): List<EventModel>
    {
        return withContext(Dispatchers.IO) {
            val donation = serviceApi.get(email,id)
            donation.body() ?: emptyList()
        }
    }

    suspend fun insert(email: String,
                       donation: EventModel) : DonationWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.post(email, donation)
            wrapper
        }
    }

    suspend fun update(email: String,
                       donation: EventModel) : DonationWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.put(email, donation._id,donation)
            wrapper
        }
    }

    suspend fun delete(email: String,
                       donation: EventModel) : DonationWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.delete(email, donation._id)
            wrapper
        }

    }

    suspend fun getEventByDate(date: String): EventModel? {
        return DonationDAO.getByDate(date)
    }

}


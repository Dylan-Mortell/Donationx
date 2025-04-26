package ie.setu.EventPlanner.data.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ie.setu.EventPlanner.data.EventModel
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

class DonationWrapper {
    var message: String? = null
    var data: EventModel? = null
}

class ServiceEndPoints {
    companion object {
        const val BASE_URL= "https://donationxweb-single-server.onrender.com/"
        const val DONATIONS_ENDPOINT = "donations"
    }
}

interface DonationService {

    @GET(ServiceEndPoints.DONATIONS_ENDPOINT + "/{email}")
    suspend fun getall(
        @Path("email") email: String)
            : Response<List<EventModel>>

    @GET(ServiceEndPoints.DONATIONS_ENDPOINT + "/{email}" + "/{id}")
    suspend fun get(@Path("email") email: String,
                    @Path("id") id: String): Response<List<EventModel>>

    @DELETE(ServiceEndPoints.DONATIONS_ENDPOINT + "/{email}" + "/{id}")
    suspend fun delete(@Path("email") email: String,
                       @Path("id") id: String): DonationWrapper

    @POST(ServiceEndPoints.DONATIONS_ENDPOINT + "/{email}")
    suspend fun post(@Path("email") email: String,
                     @Body donation: EventModel): DonationWrapper

    @PUT(ServiceEndPoints.DONATIONS_ENDPOINT + "/{email}" + "/{id}")
    suspend fun put(@Path("email") email: String,
                    @Path("id") id: String,
                    @Body donation: EventModel
    ): DonationWrapper
}

class RetrofitRepository @Inject
constructor(private val serviceApi: DonationService)  {

    suspend fun getAll(): List<EventModel>
    {
        return withContext(Dispatchers.IO) {
            val donations = serviceApi.getall()
            donations.body() ?: emptyList()
        }
    }

    suspend fun get(id: String): List<EventModel>
    {
        return withContext(Dispatchers.IO) {
            val donation = serviceApi.get(id)
            donation.body() ?: emptyList()
        }
    }

    suspend fun insert(donation: EventModel) : DonationWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.post(donation)
            wrapper
        }
    }

    suspend fun update(donation: EventModel) : DonationWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.put(donation._id,donation)
            wrapper
        }
    }

    suspend fun delete(donation: EventModel) : DonationWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.delete(donation._id)
            wrapper
        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object RetrofitModule  {
        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(ServiceEndPoints.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        fun provideServiceApi(retrofit: Retrofit): DonationService {
            return retrofit.create(DonationService::class.java)
        }

        @Provides
        fun provideRetrofitRepository( serviceApi: DonationService):
                RetrofitRepository = RetrofitRepository(serviceApi)
    }

}



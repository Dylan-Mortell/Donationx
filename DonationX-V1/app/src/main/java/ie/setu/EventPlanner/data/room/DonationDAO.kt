package ie.setu.EventPlanner.data.room

import android.content.Context
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import androidx.room.Update
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import ie.setu.EventPlanner.data.EventModel
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow

@Dao
interface DonationDAO {
    @Query("SELECT * FROM eventmodel")
    fun getAll(): Flow<List<EventModel>>

    @Insert
    suspend fun insert(donation: EventModel)

    @Query("UPDATE eventmodel SET message=:message WHERE id = :id")
    suspend fun update(id: Int, message:String)

    @Delete
    suspend fun delete(donation: EventModel)

    @Query("SELECT * FROM eventmodel WHERE id=:id")
    fun get(id: Int): Flow<EventModel>

}

@Provides
@Singleton
fun provideAppDatabase(@ApplicationContext context: Context):
        AppDatabase =
    Room.databaseBuilder(context, AppDatabase::class.java, "donation_database")
        .fallbackToDestructiveMigration()
        .build()


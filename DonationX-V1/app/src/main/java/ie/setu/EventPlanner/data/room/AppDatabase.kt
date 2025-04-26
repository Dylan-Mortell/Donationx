package ie.setu.EventPlanner.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ie.setu.EventPlanner.data.EventModel

@Database(entities = [EventModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDonationDAO(): DonationDAO
}

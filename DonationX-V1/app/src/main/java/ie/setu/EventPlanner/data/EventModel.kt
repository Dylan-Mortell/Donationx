package ie.setu.EventPlanner.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId
import com.google.gson.annotations.SerializedName
import java.util.Date
import kotlin.random.Random

@Entity
data class EventModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @DocumentId val _id: String = "N/A",
    @SerializedName("paymenttype")
    val paymentType: String = "N/A",
    @SerializedName("paymentamount")
    val paymentAmount: Int = 0,
    var message: String = "Here is a donation!",
    @SerializedName("datedonated")
    val dateDonated: Date = Date()
)

//additional code for planner
@Entity
data class PlannerModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @DocumentId val _id: String = "N/A",
    val title: String = "",
    val date: Date = Date(),
    val isCompleted: Boolean = false
)

val fakeDonations = List(30) { i ->
    EventModel(id = 12345 + i,
        _id = "12345" + i,
        "PayPal $i",
        i.toInt(),
        "Message $i",
        Date()
    )

}



var email: String = "joe@bloggs.com"


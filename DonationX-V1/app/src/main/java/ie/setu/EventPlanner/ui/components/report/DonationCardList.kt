package ie.setu.donationx.ui.components.report

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import ie.setu.EventPlanner.data.EventModel
import ie.setu.EventPlanner.data.fakeDonations
import ie.setu.EventPlanner.ui.theme.EventPlannerTheme
import java.text.DateFormat

@Composable
internal fun DonationCardList(
    donations: List<EventModel>,
    modifier: Modifier = Modifier,
    onDeleteDonation: (EventModel) -> Unit,
    onClickDonationDetails: (Int) -> Unit,
    onRefreshList: () -> Unit,
) {
    LazyColumn {
        items(
            items = donations,
            key = { donation -> donation._id }
        ) { donation ->
            DonationCard(
                paymentType = donation.paymentType,
                paymentAmount = donation.paymentAmount,
                message = donation.message,
                dateCreated = DateFormat.getDateTimeInstance().format(donation.dateDonated),
                onClickDelete = { onDeleteDonation(donation) },
                onClickDonationDetails = { onClickDonationDetails(donation.id) },
                onRefreshList = onRefreshList
            )
        }
    }
}

@Preview(showBackground = true,
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE
)
@Composable
fun DonationCardListPreview() {
    EventPlannerTheme {
        DonationCardList(
            fakeDonations.toMutableStateList(),
            onDeleteDonation = {},
            onClickDonationDetails = { },
            onRefreshList = { reportViewModel.getDonations() },
        )
    }
}

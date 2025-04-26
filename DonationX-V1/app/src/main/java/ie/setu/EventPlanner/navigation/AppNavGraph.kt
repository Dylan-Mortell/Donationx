package ie.setu.EventPlanner.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ie.setu.EventPlanner.data.EventModel
import ie.setu.EventPlanner.ui.screens.details.DetailsScreen
import ie.setu.EventPlanner.ui.screens.donate.ScreenDonate
import ie.setu.EventPlanner.ui.screens.report.ScreenReport

@Composable
fun NavHostProvider(
    modifier: Modifier,
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Report.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        composable(route = Donate.route) {
            // Call our 'Donate' Screen Here
            ScreenDonate(modifier = modifier)  // No need to pass donations
        }
        composable(route = Report.route) {
            // Call our 'Report' Screen Here
            ScreenReport(
                modifier = modifier,
                onClickDonationDetails = { donationId: Int ->
                    navController.navigateToDonationDetails(donationId)
                }
            )
        }
        composable(
            route = Details.route,
            arguments = Details.arguments
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt(Details.idArg)
            if (id != null) {
                DetailsScreen()
            }
        }
    }
}

private fun NavHostController.navigateToDonationDetails(donationId: Int) {
    this.navigate("details/$donationId")
}


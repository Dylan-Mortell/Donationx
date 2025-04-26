package ie.setu.EventPlanner.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface AppDestination {
    val icon: ImageVector
    val label: String
    val route: String
}

object Report : AppDestination {
    override val icon = Icons.AutoMirrored.Filled.List
    override val label = "Report"
    override val route = "report"
}

object Donate : AppDestination {
    override val icon = Icons.Filled.AddCircle
    override val label = "Donate"
    override val route = "donate"
}


object Details : AppDestination {
    override val icon = Icons.Filled.Details
    override val label = "Details"
    const val idArg = "id"
    override val route = "details/{$idArg}"
    val arguments = listOf(
        navArgument(idArg) { type = NavType.StringType }
    )
}

object Map : AppDestination {
    override val icon = Icons.Filled.LocationOn
    override val label = "Map"
    override val route = "map"
}




val bottomAppBarDestinations = listOf(Donate, Report)
val allDestinations = listOf(Report, Donate)

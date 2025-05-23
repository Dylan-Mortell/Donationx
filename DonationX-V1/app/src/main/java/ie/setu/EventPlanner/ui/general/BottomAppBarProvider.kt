package ie.setu.EventPlanner.ui.general

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ie.setu.EventPlanner.navigation.AppDestination
import ie.setu.EventPlanner.navigation.bottomAppBarDestinations
import ie.setu.EventPlanner.ui.theme.EventPlannerTheme

@Composable
fun BottomAppBarProvider(
    navController: NavHostController,
    currentScreen: AppDestination
) {
    //initializing the default selected item
    var navigationSelectedItem by remember { mutableIntStateOf(0) }

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onSecondary,
    ) {
        //getting the list of bottom navigation items
        bottomAppBarDestinations.forEachIndexed { index, navigationItem ->
            //iterating all items with their respective indexes
            NavigationBarItem(
                selected = navigationItem == currentScreen,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.secondary,
                    selectedTextColor = White,
                    unselectedIconColor = White,
                    unselectedTextColor = Black
                ),
                label = {
                    Text(text = navigationItem.label)
                },
                icon = {
                    Icon(
                        navigationItem.icon,
                        contentDescription = navigationItem.label
                    )
                },
                onClick = {
                    navigationSelectedItem = index
                    navController.navigate(navigationItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomAppBarScreenPreview() {
    EventPlannerTheme  {
        BottomAppBarProvider(
            rememberNavController(),
            bottomAppBarDestinations.get(1))
    }

    val bottomAppBarDestinations = listOf(Donate, Map, Report, About, Profile)

}
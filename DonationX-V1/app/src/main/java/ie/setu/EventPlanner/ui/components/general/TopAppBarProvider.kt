package ie.setu.EventPlanner.ui.components.general

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ie.setu.donationx.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarProvider(selectedMenuItem: MenuItem?,
                      onSelectedMenuItemChange: (MenuItem?) -> Unit
    ) {

    var selectedMenuItem = selectedMenuItem
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        actions = {
            if(selectedMenuItem == MenuItem.Donate) {
                IconButton(onClick = {
                    selectedMenuItem = MenuItem.Report
                    onSelectedMenuItemChange(selectedMenuItem)
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.List,
                        contentDescription = "Options",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
            else {
                IconButton(onClick = {
                    selectedMenuItem = MenuItem.Donate
                    onSelectedMenuItemChange(selectedMenuItem)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Options",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }
    )
}
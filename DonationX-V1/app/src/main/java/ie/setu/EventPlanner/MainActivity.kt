package ie.setu.EventPlanner



import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ie.setu.EventPlanner.data.EventModel
import ie.setu.EventPlanner.navigation.AppDestination
import ie.setu.EventPlanner.navigation.Donate
import ie.setu.EventPlanner.navigation.NavHostProvider
import ie.setu.EventPlanner.navigation.Report
import ie.setu.EventPlanner.navigation.allDestinations
import ie.setu.EventPlanner.ui.components.general.MenuItem
import ie.setu.EventPlanner.ui.general.BottomAppBarProvider
import ie.setu.EventPlanner.ui.theme.EventPlannerTheme
import ie.setu.donationx.R


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventPlannerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EventPlannerApp(modifier = Modifier)
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EventPlannerApp(modifier: Modifier = Modifier,
                    navController: NavHostController = rememberNavController())
 {
     val donations = remember { mutableStateListOf<EventModel>() }
     var selectedMenuItem by remember { mutableStateOf<MenuItem?>(MenuItem.Donate) }
     val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
     val currentDestination = currentNavBackStackEntry?.destination
     val currentBottomScreen =
         allDestinations.find { it.route == currentDestination?.route } ?: Report


     Scaffold(
        modifier = modifier,
         topBar = {
             TopAppBarProvider(
                 currentScreen = currentBottomScreen,
                 canNavigateBack = navController.previousBackStackEntry != null
             ) { navController.navigateUp() }
         },
        content = { paddingValues ->
            NavHostProvider(
                modifier = modifier,
                navController = navController,
                paddingValues = paddingValues)
        },
        bottomBar = {
            BottomAppBarProvider(navController,
                currentScreen = currentBottomScreen,)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarProvider(
    currentScreen: AppDestination,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {})
{
    TopAppBar(
        title = {
            Text(
                text = currentScreen.label,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back Button",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            else
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu Button",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )

        },
        actions = { /*DropDownMenu()*/ }
    )
}
@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    EventPlannerTheme {
        TopAppBarProvider(Donate,
            true)
    }
}


@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    EventPlannerTheme {
        EventPlannerApp(modifier = Modifier)
    }


    class MainActivity : AppCompatActivity() {

        private lateinit var username: EditText
        private lateinit var password: EditText
        private lateinit var loginButton: Button

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            username = findViewById(R.id.username)
            password = findViewById(R.id.password)
            loginButton = findViewById(R.id.loginButton)

            loginButton.setOnClickListener {
                val enteredUsername = username.text.toString()
                val enteredPassword = password.text.toString()

                if (enteredUsername == "user" && enteredPassword == "1234") {
                    Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}
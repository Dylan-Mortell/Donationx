package ie.setu.EventPlanner.ui.screens.profile

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ie.setu.EventPlanner.R
import ie.setu.EventPlanner.ui.components.general.ShowPhotoPicker
import ie.setu.EventPlanner.ui.components.general.HeadingTextComponent
import ie.setu.EventPlanner.ui.screens.login.LoginViewModel
import ie.setu.EventPlanner.ui.screens.register.RegisterViewModel

@Composable
fun ProfileScreen(
    onSignOut: () -> Unit = {},
    profileViewModel: ProfileViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel(),
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    val photoUri: Uri? by remember { mutableStateOf(profileViewModel.photoUri) }
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        HeadingTextComponent(value = stringResource(id = R.string.account_settings))
        Spacer(modifier = Modifier.height(10.dp))

        if(photoUri.toString().isNotEmpty())
            ProfileContent(
                photoUri = photoUri,
                displayName = profileViewModel.displayName,
                email = profileViewModel.email
            )
        Button(
            onClick = {
                profileViewModel.signOut()
                onSignOut()
                loginViewModel.resetLoginFlow()
                registerViewModel.resetRegisterFlow()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
        ) {
            Text(text = "Logout")
        }

        var photoUri: Uri? by remember { mutableStateOf(profileViewModel.photoUri) }


        ShowPhotoPicker(
            onPhotoUriChanged = {
                photoUri = it
                profileViewModel.updatePhotoUri(photoUri!!)
            }
        )

    }}

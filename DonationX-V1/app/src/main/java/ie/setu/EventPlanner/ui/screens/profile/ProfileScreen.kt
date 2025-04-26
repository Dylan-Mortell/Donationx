package ie.setu.EventPlanner.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

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

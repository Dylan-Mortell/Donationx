package ie.setu.EventPlanner.ui.components.general


@Composable
fun ShowPhotoPicker(
    onPhotoUriChanged: (Uri) -> Unit,
) {
    val context = LocalContext.current
    var photoUri: Uri? by remember { mutableStateOf(null) }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()) { uri ->
        //When the user has selected a photo, its URI is returned here
        uri?.let {
            //Get READ Permissions for local storage
            val name = context.packageName
            context.grantUriPermission(name, uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)

            onPhotoUriChanged(uri)
        }
        photoUri = uri
    }
    Column {
        Button(
            onClick = {
                //On button press, launch the photo picker
                launcher.launch(
                    PickVisualMediaRequest(
                        //Here we request only photos.
                        // Change this to .ImageAndVideo if you want videos too.
                        //Or use .VideoOnly if you only want videos.
                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            }
        ) {
            Text("Change Profile Photo")
        }
    }}

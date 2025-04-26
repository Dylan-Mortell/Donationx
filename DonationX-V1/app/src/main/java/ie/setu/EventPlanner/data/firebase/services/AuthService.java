package ie.setu.EventPlanner.data.firebase.services;

import android.net.Uri;

import com.google.firebase.auth.FirebaseUser
import ie.setu.donationx.firebase.auth.Response

typealias FirebaseSignInResponse = Response<FirebaseUser>

interface AuthService {
    val currentUserId: String
    val currentUser: FirebaseUser?
    val isUserAuthenticatedInFirebase: Boolean

    suspend fun authenticateUser(email: String, password: String)
                : FirebaseSignInResponse
    suspend fun createUser(name: String, email: String, password: String)
                : FirebaseSignInResponse
    suspend fun signOut()

    val email: String?

    val customPhotoUri: Uri?

    suspend fun updatePhoto(uri: Uri) : FirebaseSignInResponse


}
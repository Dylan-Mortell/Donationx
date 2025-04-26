package ie.setu.EventPlanner.data.firebase.auth

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import ie.setu.donationx.firebase.services.AuthService
import ie.setu.donationx.firebase.services.FirebaseSignInResponse
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository
@Inject constructor(private val firebaseAuth: FirebaseAuth, )
    : AuthService {

    override val currentUserId: String
        get() = firebaseAuth.currentUser?.uid.orEmpty()

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override val isUserAuthenticatedInFirebase: Boolean
        get() = firebaseAuth.currentUser != null

    override suspend fun authenticateUser(email: String, password: String): FirebaseSignInResponse {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Response.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun createUser(name: String, email: String, password: String): FirebaseSignInResponse {
        return try {
            val uri = Uri.parse("android.resource://ie.setu.donationx/drawable/aboutus_homer")
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result.user?.updateProfile(UserProfileChangeRequest
                .Builder()
                .setDisplayName(name)
                .setPhotoUri(uri)
                .build())?.await()
            return Response.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }


    override suspend fun signOut() {
        firebaseAuth.signOut()
    }

    override val email: String?
        get() = firebaseAuth.currentUser?.email

    override val customPhotoUri: Uri?
        get() = firebaseAuth.currentUser!!.photoUrl


    override suspend fun updatePhoto(uri: Uri) : FirebaseSignInResponse {
        return try {
            currentUser!!.updateProfile(UserProfileChangeRequest
                .Builder()
                .setPhotoUri(uri)
                .build()).await()
            return Response.Success(currentUser!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }


}

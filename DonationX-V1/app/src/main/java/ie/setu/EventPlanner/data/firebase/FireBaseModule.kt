package ie.setu.EventPlanner.data.firebase

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ie.setu.EventPlanner.data.firebase.auth.AuthRepository
import ie.setu.EventPlanner.data.firebase.database.FirestoreRepository
import ie.setu.EventPlanner.data.firebase.services.FirestoreService
import ie.setu.donationx.firebase.auth.AuthRepository
import ie.setu.donationx.firebase.services.AuthService

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    fun provideAuthRepository(auth: FirebaseAuth):
            AuthService = AuthRepository(firebaseAuth = auth)

    @Provides
    fun provideFirebaseFirestore()
            : FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideFirestoreRepository(
        auth: AuthService,
        firebaseFirestore: FirebaseFirestore
    ) : FirestoreService = FirestoreRepository(
        auth = auth,
        firestore = firebaseFirestore
    )


}
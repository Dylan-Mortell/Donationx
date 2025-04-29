package ie.setu.EventPlanner

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class `SplashActivity.kt` : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Optional: Add a short delay or setup logic
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

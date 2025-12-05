package id.ac.pnm.anterinapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import id.ac.pnm.anterinapp.R
import id.ac.pnm.anterinapp.activity.RegisterActivity

class OnboardingActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_2)

        val buttonLogin = findViewById<Button>(R.id.btnMulai)
        val buttonRegister = findViewById<Button>(R.id.btnDaftar)

        buttonLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        buttonRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
package id.ac.pnm.anterinapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import id.ac.pnm.anterinapp.activity.DashboardActivity
import id.ac.pnm.anterinapp.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        val buttonLogin = findViewById<Button>(R.id.btnLogin)
        val registerLink = findViewById<TextView>(R.id.tvRegisterLink)

        buttonLogin.setOnClickListener {
            val etEmail = findViewById<EditText>(R.id.etEmail)
            val usernameInput = etEmail.text.toString()

            if (usernameInput.isNotEmpty()) {
                val intent = Intent(this, DashboardActivity::class.java)

                intent.putExtra("USERNAME", usernameInput)

                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Isi username dulu", Toast.LENGTH_SHORT).show()
            }
        }

        registerLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
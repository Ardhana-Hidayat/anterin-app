package id.ac.pnm.anterinapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AddTripActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_add_trip)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val etDate = findViewById<EditText>(R.id.etDate)

        btnBack.setOnClickListener {
            finish()
        }

        etDate.setOnClickListener {
        }

        btnSave.setOnClickListener {
            val intent = Intent(this, SuccessActivity::class.java)
            startActivity(intent)
        }
    }
}
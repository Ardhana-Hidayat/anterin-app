package id.ac.pnm.anterinapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.ImageView
import android.widget.Button
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SelectPickUpPoint : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_select_pickup_point)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvCurrentLocation = findViewById<TextView>(R.id.tvCurrentLocation)
        val tvPickupLocation = findViewById<TextView>(R.id.tvPickupLocation)
        val tvTime = findViewById<TextView>(R.id.tvTime)
        val tvCost = findViewById<TextView>(R.id.tvCost)
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnConfirm = findViewById<Button>(R.id.btnConfirm)
        val cardPickup = findViewById<View>(R.id.cardPickup)

        tvCurrentLocation.text = "Jl. Serayu, Kota Madiun"
        tvPickupLocation.text = "Jl. Serayu, Kota Madiun"
        tvTime.text = "Menghemat Waktu: 5 menit"
        tvCost.text = "Menghemat Biaya: Rp 2.000"

        btnBack.setOnClickListener {
            finish()
        }

        btnConfirm.setOnClickListener {
            val lokasi = tvPickupLocation.text.toString()

            val intent = Intent(this, OrderSuccessful::class.java)
            intent.putExtra("LOKASI_JEMPUT", lokasi)
            startActivity(intent)
        }

        cardPickup.setOnClickListener {
            Toast.makeText(this, "Fitur peta belum tersedia", Toast.LENGTH_SHORT).show()
        }
    }
}
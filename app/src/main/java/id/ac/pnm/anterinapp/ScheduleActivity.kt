package id.ac.pnm.anterinapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ScheduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_schedule)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)

        // Aksi tombol Back (kembali ke halaman sebelumnya/Home)
        btnBack.setOnClickListener {
            finish()
        }

        // Aksi tombol FAB (+), pindah ke halaman Tambah Perjalanan
        fabAdd.setOnClickListener {
            // Pastikan AddTripActivity adalah nama class Activity form Anda yang sebelumnya
            val intent = Intent(this, AddTripActivity::class.java)
            startActivity(intent)
        }
    }
}
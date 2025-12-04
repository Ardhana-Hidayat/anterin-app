package id.ac.pnm.anterinapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mengaktifkan mode layar penuh (status bar transparan)
        enableEdgeToEdge()

        // Menghubungkan ke activity_main.xml yang baru (yang isinya NavHost)
        setContentView(R.layout.activity_main)

        // Mengatur padding agar konten tidak tertutup status bar/navigasi bawah HP
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
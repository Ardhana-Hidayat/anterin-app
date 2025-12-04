package id.ac.pnm.anterinapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class DetailJadwalFragment : Fragment(R.layout.fragment_detail_jadwal) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack = view.findViewById<ImageView>(R.id.btnBack)
        val tvSubHeader = view.findViewById<TextView>(R.id.tvSubHeader) // Judul Biru

        // 1. Ambil Data yang dikirim dari ScheduleFragment
        val judulDiterima = arguments?.getString("JUDUL_JADWAL")

        // 2. Tampilkan datanya
        if (judulDiterima != null) {
            tvSubHeader.text = judulDiterima
        }

        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
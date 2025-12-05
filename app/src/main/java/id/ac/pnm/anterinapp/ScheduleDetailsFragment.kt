package id.ac.pnm.anterinapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ScheduleDetailsFragment : Fragment(R.layout.fragment_schedule_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack = view.findViewById<ImageView>(R.id.btnBack)
        val tvSubHeader = view.findViewById<TextView>(R.id.tvSubHeader)
        val judulDiterima = arguments?.getString("JUDUL_JADWAL")

        if (judulDiterima != null) {
            tvSubHeader.text = judulDiterima
        }

        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
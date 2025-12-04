package id.ac.pnm.anterinapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class AddTripFragment : Fragment(R.layout.fragment_add_trip) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack = view.findViewById<ImageView>(R.id.btnBack)
        val btnSave = view.findViewById<Button>(R.id.btnSave)

        // Kembali ke halaman sebelumnya
        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        // Simpan -> Ke Halaman Sukses
        btnSave.setOnClickListener {
            findNavController().navigate(R.id.action_addTrip_to_success)
        }
    }
}
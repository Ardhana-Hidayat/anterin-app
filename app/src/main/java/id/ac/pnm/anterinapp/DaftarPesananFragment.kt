package id.ac.pnm.anterinapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class DaftarPesananFragment : Fragment(R.layout.fragment_daftar_pesanan) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack = view.findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        // Contoh: Jika klik item pesanan -> Buka Detail Pesanan
        // val itemPesanan = view.findViewById<View>(R.id.itemPesanan1)
        // itemPesanan.setOnClickListener {
        //     findNavController().navigate(R.id.action_daftarPesanan_to_detailPesanan)
        // }
    }
}
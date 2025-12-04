package id.ac.pnm.anterinapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Logic Tombol Gabungan (Kode Lama)
        val cardGabungan = view.findViewById<CardView>(R.id.cardGabungan)
        cardGabungan.setOnClickListener {
            try {
                findNavController().navigate(R.id.combinationFragment)
            } catch (e: Exception) {
                // Ignore jika belum ada
            }
        }

        // ==========================================
        // 2. LOGIC TOMBOL BARU (Jadwal & Pesanan)
        // ==========================================

        // Klik Jadwal
        val btnMenuJadwal = view.findViewById<CardView>(R.id.btnMenuJadwal)
        btnMenuJadwal?.setOnClickListener {
            // Pastikan ID Action ini sudah ada di nav_graph.xml
            findNavController().navigate(R.id.action_home_to_schedule)
        }

        // Klik Daftar Pesanan
        val btnMenuPesanan = view.findViewById<CardView>(R.id.btnMenuPesanan)
        btnMenuPesanan?.setOnClickListener {
            // Pastikan ID Action ini sudah ada di nav_graph.xml
            findNavController().navigate(R.id.action_home_to_daftarPesanan)
        }
        // ==========================================


        // 3. Logic RecyclerView History (Kode Lama)
        val rvHistoryDashboard = view.findViewById<RecyclerView>(R.id.rvHistoryDashboard)

        // Pastikan Anda punya class HistoryData dan HistoryAdapter
        val historyList = listOf(
            HistoryData("Ke Kampus", "10 November 2025", "Selesai"),
            HistoryData("Stasiun Solo Balapan", "10 November 2025", "Selesai"),
            HistoryData("Pasar Besar Madiun", "09 November 2025", "Dibatalkan"),
            HistoryData("Mall Madiun", "08 November 2025", "Selesai")
        )

        rvHistoryDashboard.layoutManager = LinearLayoutManager(context)
        // Pastikan HistoryAdapter sudah ada
        val adapter = HistoryAdapter(historyList)
        rvHistoryDashboard.adapter = adapter
    }
}
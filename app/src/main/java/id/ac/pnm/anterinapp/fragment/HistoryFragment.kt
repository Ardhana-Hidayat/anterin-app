package id.ac.pnm.anterinapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// --- IMPORT PENTING (Agar R terbaca) ---
import id.ac.pnm.anterinapp.R
import id.ac.pnm.anterinapp.adapter.HistoryAdapter
import id.ac.pnm.anterinapp.model.HistoryData

class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataList = listOf(
            HistoryData("Ke Stasiun Madiun", "12 Nov 2025, 08:00", "Selesai"),
            HistoryData("Ke Kampus PNM", "11 Nov 2025, 07:30", "Dibatalkan"),
            HistoryData("Jalan Pahlawan", "10 Nov 2025, 19:00", "Selesai"),
            HistoryData("Pasar Besar", "09 Nov 2025, 10:15", "Selesai"),
            HistoryData("Alun-Alun Madiun", "08 Nov 2025, 16:45", "Dalam Proses"),
            HistoryData("Sun City Mall", "05 Nov 2025, 13:20", "Dibatalkan"),
            HistoryData("Terminal Purbaya", "01 Nov 2025, 06:00", "Selesai")
        )

        val rvHistory = view.findViewById<RecyclerView>(R.id.rvHistory)
        rvHistory.layoutManager = LinearLayoutManager(context)

        val adapter = HistoryAdapter(dataList)
        rvHistory.adapter = adapter
    }
}
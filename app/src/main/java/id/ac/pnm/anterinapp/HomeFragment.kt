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

        val cardGabungan = view.findViewById<CardView>(R.id.cardGabungan)
        cardGabungan.setOnClickListener {
            try {
                findNavController().navigate(R.id.combinationFragment)
            } catch (e: Exception) {
                Toast.makeText(context, "Fitur belum dipasang di NavGraph", Toast.LENGTH_SHORT).show()
            }
        }

        val rvHistoryDashboard = view.findViewById<RecyclerView>(R.id.rvHistoryDashboard)

        val historyList = listOf(
            HistoryData("Ke Kampus", "10 November 2025", "Selesai"),
            HistoryData("Stasiun Solo Balapan", "10 November 2025", "Selesai"),
            HistoryData("Pasar Besar Madiun", "09 November 2025", "Dibatalkan"), // Coba variasi status
            HistoryData("Mall Madiun", "08 November 2025", "Selesai")
        )

        rvHistoryDashboard.layoutManager = LinearLayoutManager(context)

        val adapter = HistoryAdapter(historyList)
        rvHistoryDashboard.adapter = adapter
    }
}
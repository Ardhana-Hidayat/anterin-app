package id.ac.pnm.anterinapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ScheduleFragment : Fragment(R.layout.fragment_schedule) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataJadwal = listOf(
            Jadwal("1", "Berangkat ke Kampus", "10 November 2025"),
            Jadwal("2", "Pulang ke Rumah", "10 November 2025"),
            Jadwal("3", "Ke Stasiun Madiun", "11 November 2025"),
            Jadwal("4", "Jalan-jalan sore", "12 November 2025")
        )

        val btnBack = view.findViewById<ImageView>(R.id.btnBack)

        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        val rvJadwal = view.findViewById<RecyclerView>(R.id.rvJadwal)
        rvJadwal.layoutManager = LinearLayoutManager(context)

        val adapter = JadwalAdapter(dataJadwal) { jadwalTerpilih ->

            val bundle = bundleOf("JUDUL_JADWAL" to jadwalTerpilih.judul)

            findNavController().navigate(R.id.action_schedule_to_detailJadwal, bundle)
        }
        rvJadwal.adapter = adapter

        val fabAdd = view.findViewById<FloatingActionButton>(R.id.fabAdd)
        fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_schedule_to_addTrip)
        }
    }
}
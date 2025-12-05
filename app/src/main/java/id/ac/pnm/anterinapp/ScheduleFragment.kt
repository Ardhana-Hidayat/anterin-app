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

        val dataSchedules = listOf(
            Schedule("1", "Berangkat ke Kampus", "10 November 2025"),
            Schedule("2", "Pulang ke Rumah", "10 November 2025"),
            Schedule("3", "Ke Stasiun Madiun", "11 November 2025")
        )

        val rvJadwal = view.findViewById<RecyclerView>(R.id.rvJadwal)
        rvJadwal.layoutManager = LinearLayoutManager(context)

        val adapter = ScheduleAdapter(dataSchedules) { jadwalTerpilih ->
            val bundle = bundleOf("JUDUL_JADWAL" to jadwalTerpilih.judul)
            findNavController().navigate(R.id.action_schedule_to_detailJadwal, bundle)
        }
        rvJadwal.adapter = adapter

        val btnBack = view.findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        val fabAdd = view.findViewById<FloatingActionButton>(R.id.fabAdd)
        fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_schedule_to_addTrip)
        }
    }
}
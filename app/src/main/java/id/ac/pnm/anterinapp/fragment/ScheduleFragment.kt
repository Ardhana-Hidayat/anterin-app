package id.ac.pnm.anterinapp.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

import id.ac.pnm.anterinapp.R
import id.ac.pnm.anterinapp.adapter.ScheduleAdapter
import id.ac.pnm.anterinapp.model.ScheduleData

class ScheduleFragment : Fragment(R.layout.fragment_schedule) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataSchedule = listOf(
            ScheduleData("1", "Berangkat ke Kampus", "10 November 2025"),
            ScheduleData("2", "Pulang ke Rumah", "10 November 2025"),
            ScheduleData("3", "Ke Stasiun Madiun", "11 November 2025")
        )

        val rvJadwal = view.findViewById<RecyclerView>(R.id.rvJadwal)
        rvJadwal.layoutManager = LinearLayoutManager(context)

        //set up schedule adapter dan memberi input (data list dan fungsi navigasi)
        val adapter = ScheduleAdapter(dataSchedule) { jadwalTerpilih ->
            val bundle = bundleOf("JUDUL_JADWAL" to jadwalTerpilih.judul)
            findNavController().navigate(R.id.action_schedule_to_scheduleDetail, bundle)
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
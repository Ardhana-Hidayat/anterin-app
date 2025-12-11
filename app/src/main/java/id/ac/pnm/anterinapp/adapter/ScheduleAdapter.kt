package id.ac.pnm.anterinapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.pnm.anterinapp.R
import id.ac.pnm.anterinapp.model.ScheduleData

class ScheduleAdapter(
    val listScheduleData: List<ScheduleData>,
    val onItemClick: (ScheduleData) -> Unit
) : RecyclerView.Adapter<ScheduleAdapter.JadwalViewHolder>() {

    class JadwalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvItemTitle)
        val tvDate: TextView = view.findViewById(R.id.tvItemDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_schedule, parent, false)
        return JadwalViewHolder(view)
    }

    override fun onBindViewHolder(holder: JadwalViewHolder, position: Int) {
        val item = listScheduleData[position]
        holder.tvTitle.text = item.judul
        holder.tvDate.text = item.tanggal

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount() = listScheduleData.size
}
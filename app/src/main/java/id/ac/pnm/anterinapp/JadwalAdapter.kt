package id.ac.pnm.anterinapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JadwalAdapter(
    private val listJadwal: List<Jadwal>,
    private val onItemClick: (Jadwal) -> Unit
) : RecyclerView.Adapter<JadwalAdapter.JadwalViewHolder>() {

    class JadwalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvItemTitle)
        val tvDate: TextView = view.findViewById(R.id.tvItemDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_jadwal, parent, false)
        return JadwalViewHolder(view)
    }

    override fun onBindViewHolder(holder: JadwalViewHolder, position: Int) {
        val item = listJadwal[position]
        holder.tvTitle.text = item.judul
        holder.tvDate.text = item.tanggal

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount() = listJadwal.size
}
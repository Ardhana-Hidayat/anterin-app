package id.ac.pnm.anterinapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.pnm.anterinapp.model.HistoryData
import id.ac.pnm.anterinapp.R

class HistoryAdapter(private val historyList: List<HistoryData>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvHistoryTitle)
        val tvDate: TextView = view.findViewById(R.id.tvHistoryDate)
        val tvStatus: TextView = view.findViewById(R.id.tvHistoryStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = historyList[position]

        holder.tvTitle.text = item.title
        holder.tvDate.text = item.date
        holder.tvStatus.text = item.status

        when (item.status) {
            "Selesai" -> {
                holder.tvStatus.setTextColor(Color.parseColor("#4CAF50"))
            }
            "Dibatalkan" -> {
                holder.tvStatus.setTextColor(Color.parseColor("#F44336"))
            }
            "Dalam Proses" -> {
                holder.tvStatus.setTextColor(Color.parseColor("#FF9800"))
            }
            else -> {
                holder.tvStatus.setTextColor(Color.parseColor("#999999"))
            }
        }
    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}
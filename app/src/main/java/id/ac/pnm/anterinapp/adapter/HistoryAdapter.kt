package id.ac.pnm.anterinapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.pnm.anterinapp.R
import id.ac.pnm.anterinapp.model.HistoryData

class HistoryAdapter(
    val originalList: List<HistoryData>,
    val onItemClick: (HistoryData) -> Unit
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    var displayList = ArrayList<HistoryData>()

    init {
        displayList.addAll(originalList)
    }

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
        val item = displayList[position]

        holder.tvTitle.text = item.title
        holder.tvDate.text = item.date
        holder.tvStatus.text = item.status

        when (item.status) {
            "Selesai" -> holder.tvStatus.setTextColor(Color.parseColor("#4CAF50"))
            "Dibatalkan" -> holder.tvStatus.setTextColor(Color.parseColor("#F44336"))
            "Dalam Proses" -> holder.tvStatus.setTextColor(Color.parseColor("#FF9800"))
            else -> holder.tvStatus.setTextColor(Color.parseColor("#999999"))
        }

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = displayList.size

    fun filterList(query: String) {
        displayList.clear()
        if (query.isEmpty()) {
            displayList.addAll(originalList)
        } else {
            val searchLower = query.lowercase()
            for (item in originalList) {
                if (item.title.lowercase().contains(searchLower) ||
                    item.date.lowercase().contains(searchLower)) {
                    displayList.add(item)
                }
            }
        }

        notifyDataSetChanged()
    }
}